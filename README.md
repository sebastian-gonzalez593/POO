4.	TEMA DE PROYECTOS NRO. 4.: Sistema de Reservas y Control de Visitas a Museos y Centros Culturales de Loja.

La Dirección de Cultura y Patrimonio de Loja desea implementar un sistema automatizado que permita gestionar las reservas y control de visitas de ciudadanos, turistas y grupos educativos a los principales museos y centros culturales de la ciudad. El sistema debe considerar las particularidades de cada tipo de visitante y las características únicas de cada espacio cultural.

Cada centro cultural cuenta con espacios de exhibición, actividades programadas y limitaciones de aforo. Algunos eventos requieren reserva anticipada y validación del acceso en puntos de control. Las visitas pueden ser individuales, guiadas o grupales, cada una con procesos y reglas específicas de registro y seguimiento.

El sistema debe poder procesar distintos tipos de usuarios, registrar múltiples visitas, generar reportes de asistencia por tipo de evento, e incluir un módulo que permita calcular indicadores como la ocupación promedio, cancelaciones o visitantes recurrentes. Los reportes deben adaptarse a las características del visitante y del centro visitado.

Requerimientos funcionales

1.	Permitir registrar museos y centros culturales con su respectiva capacidad máxima, ubicación, horarios y tipos de eventos culturales asociados.
2.	Gestionar distintos tipos de visitantes:
a.	Individuales (pueden reservar en línea y cancelar visitas)
b.	Educativos (grupos escolares o universitarios con guías designados)
c.	Turísticos (acceso a visitas guiadas y actividades opcionales)
3.	Cada visita debe estar asociada a una fecha, un espacio cultural y un visitante.
4.	Calcular la ocupación de cada espacio mediante: ocupacion = (visitasConfirmadas / aforoMaximo) * 100
5.	Calcular un indicador de repetición por visitante: frecuenciaVisita = visitasRealizadas / mesesActivos
6.	Procesar las reservas automáticamente según el tipo de usuario y evento.
7.	Implementar estructuras dinámicas para almacenar reservas, espacios, actividades y usuarios.
8.	Aplicar sobrecarga de métodos para el cálculo de indicadores y envío de confirmaciones.
9.	Persistir los datos en una base de datos relacional.
10.	Leer información de visitantes o espacios culturales desde archivos CSV.
11.	Manejar excepciones para operaciones como reservas inválidas, visitas fuera de horario o duplicadas.
12.	Seguir el patrón arquitectónico MVC.
13.	Aplicar principios SOLID en la distribución de responsabilidades entre clases y capas del sistema.
