# 🌟 Evolución Senior: Proyecto DDD + Hexagonal

Guía visual paso a paso para construir un proyecto limpio, escalable y mantenible, desde diseño conceptual hasta Event Sourcing completo.

---

## 🟢 Fase 0 — Diseño conceptual

🎯 Objetivo: planificar BCs, agregados y reglas sin escribir código.


📦 [Bounded Context A] 📦 [Bounded Context B]
│ │
│ Aggregate + Entities │ Aggregate + Entities
│ Invariantes críticas │ Invariantes críticas
│ Domain Services planeados │ Domain Services planeados
│ Policies planeadas │ Policies planeadas
│ │
└────── Integración ───────> (API / Eventos / Read Models)


💡 Conceptos clave:  
- Eventos: comunicación asíncrona entre BCs  
- API / Adapter: consultas en tiempo real  
- Read Models / Proyecciones: copia de datos para consultas  
- Eventual consistency: tolerancia a retrasos entre BCs

---

## 🟢 Fase 1 — Base limpia

🎯 Objetivo: arquitectura mínima funcional.


🟦 Application Service
│
v
🟩 Aggregate Root (Domain)
│
v
🟨 Repository Port <----> 🟪 Infrastructure (DB simple)


> Agregados con invariantes mínimas, repositorios como interfaces, Application Services dirigen el flujo.

---

## 🟡 Fase 2 — Casos de uso reales


[Application Service Handlers]
├─ Create
├─ Update
└─ Delete
│
v
[Domain Aggregate]
├─ Métodos de negocio
└─ Validación de invariantes
│
v
[Repository Port] ----> [Infrastructure: DB]


> Se añaden casos de uso reales y lógica de agregados.

---

## 🟠 Fase 3 — Integración con datos externos / otros BCs


[Application Service]
│
v
[Port / Interface a BC externo] <----> [Infrastructure Adapter: API / Queue / Proyección]
│
v
[Domain Aggregate] (solo recibe referencias o Value Objects)


> Desacoplamiento total: los agregados nunca conocen los detalles del BC externo.

---

## 🔵 Fase 4 — Domain Services y Policies


[Application Service]
│
v
[Domain Service / Policy]
│
v
[Domain Aggregate]


> Reglas complejas que involucran múltiples agregados, manteniendo la lógica de negocio en el dominio.

---

## 🔵 Fase 5 — CQRS básico


⚡ Commands (Application Service)
│
v
[Domain Aggregate] ---> [Event Store / Repository]

👀 Queries
│
v
[Read Models / Proyecciones] <--- [Infrastructure]


> Separación de lectura y escritura, optimizando consultas y manteniendo dominio limpio.

---

## 🟣 Fase 6 — Eventos de dominio


[Domain Aggregate]
│
v
[Domain Events] ---> [Event Handlers / Proyecciones] ---> [Otros BCs / Services]


> Desacople de procesos, preparación para Event Sourcing o integración asíncrona.

---

## 🔴 Fase 7 — Event Sourcing completo


[Event Store] <--- [Domain Aggregate reconstruido desde eventos]
│
v
[Read Models / Proyecciones] <--- [Event Handlers]


> Reconstrucción de agregados a partir de eventos, máxima escalabilidad y desacople.

---

## 💡 Principios clave


🟦 Application ---> dirige el flujo, usa puertos, no contiene lógica de negocio
🟩 Domain ---> reglas de negocio, agregados, invariantes
🟪 Domain Services / Policies ---> reglas complejas externas
🟨 Infrastructure ---> persistencia, adaptadores, datos externos


> Evolución incremental: empezar simple y escalar según necesidad.
