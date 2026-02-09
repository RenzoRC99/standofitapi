# 🏋️ Standofit Workout Template Domain – Contexto de Plantillas

Este proyecto implementa el **dominio de plantillas de entrenamiento** usando **Domain-Driven Design (DDD)**.  
Este README describe **el primer contexto del dominio: Plantillas**, que será parte de un sistema más grande con otros contextos como **Ejercicios** y **Rutinas**.

---

## 📦 Contexto: Plantillas (`WorkoutTpl`)

### Objetivo
Gestionar **plantillas de entrenamiento**, que contienen **días y ejercicios**, manteniendo reglas de negocio claras, inmutabilidad y encapsulación.

---

## 🏷️ Value Objects (VO)

Los VO representan **valores inmutables con reglas propias**:

| VO | Descripción |
|----|------------|
| `WorkoutExerciseReps` | Reps de un ejercicio (1–500) |
| `WorkoutExerciseSets` | Sets de un ejercicio |
| `WorkoutExerciseRestTime` | Tiempo de descanso entre series |
| `WorkoutExerciseDropset` | Booleano para dropset |
| `WorkoutDayNumber` | Número del día dentro de la plantilla |
| `WorkoutTplName`, `WorkoutTplDescription`, `WorkoutTplLevel` | Atributos generales de la plantilla |

**Responsabilidad:** validar cada valor antes de ser usado en una entidad.

---

## 🏋️ Entidades

### `WorkoutExercise`
- Contiene VO: sets, reps, restTime y dropset.  
- Métodos **inmutables** para modificar sus atributos.  

### `WorkoutDay`
- Contiene una **lista de `WorkoutExercise`**.  
- Métodos principales:
  - `addExercise` / `removeExercise`  
  - `changeExerciseAttributes` → delega cambios en los ejercicios  
- Mantiene **invariantes**:
  - No puede estar vacío  
  - No puede tener ejercicios duplicados  

### `WorkoutDays`
- VO de colección que contiene `WorkoutDay`.  
- Valida **no duplicados y orden de días**.  
- Métodos: `add`, `remove`, `update`, `find`  

---

## 🏆 Aggregate Root: `WorkoutTpl`

- Representa la **plantilla completa de entrenamiento**.  
- Contiene VO, `WorkoutDays` y el estado (`DRAFT`, `PUBLISHED`, `ARCHIVED`).  
- Métodos de negocio:
  - Gestión de días (`addDay`, `removeDay`)  
  - Gestión de ejercicios (delegando a `WorkoutDay`):  
    - `addExerciseToDay`  
    - `replaceExerciseInDay`  
    - Métodos expresivos para cambios de atributos (`changeRepsInDay`, `changeSetsInDay`, `toggleDropsetInDay`)  

**Encapsulación máxima:**  
- Nunca modifica directamente ejercicios ni días, solo coordina y delega.  

---

## 🔹 Principios de diseño aplicados

1. **Encapsulación por agregado:** cada entidad maneja sus propios datos y reglas.  
2. **Inmutabilidad:** cada cambio devuelve un nuevo objeto.  
3. **Separación de responsabilidades:**  
   - VO → validaciones de valor  
   - `WorkoutExercise` → atributos y reglas del ejercicio  
   - `WorkoutDay` → gestión de ejercicios y reglas del día  
   - `WorkoutDays` → colección de días, orden y duplicados  
   - `WorkoutTpl` → coherencia del agregado y coordinación de cambios  
4. **Preparado para múltiples contextos:**  
   - `WorkoutTpl` puede referenciar otros contextos (Ejercicios, Rutinas) mediante IDs, sin acoplarse a su lógica interna.
