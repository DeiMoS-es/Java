# Getting Started

### Pasos:
#### Todo lo relacionado con la seguridad se alojará en el paquete secutirty
#### - Creamos primer paquete (auth), el cual contiene el controlador para la autenticación estos endPoint de login y registro de usuarios deberían de ser públicos
#### - Creamos el segundo paquete (demo), donde se encontrarán los endpoint protegidos
#### - Continuamos con la configuración de los filtros, creo un nuevo paquete (config). Dentro de este paquete, la clase SecurityConfig es la que va a contener la cadena de filtros y ese método SecurityFilterChain
#### - Continuo con el paquete (Jwt) contiene todo lo relacionado a JWT
