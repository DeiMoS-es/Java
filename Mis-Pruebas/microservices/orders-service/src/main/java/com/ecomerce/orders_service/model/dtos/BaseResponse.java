package com.ecomerce.orders_service.model.dtos;

/**
 * Un record es parecido a una clase inmutable
 */
public record BaseResponse(String[] errorMessages) {
    // Un record no puede tener metodos, solo atributos
    // Para este caso, tendremos un arregle con los errrres que se presenten en la llamada al servicio
    public boolean hasErrors(){
        return errorMessages != null && errorMessages.length > 0;
    }

    // Método para obtener los mensajes de error
    public String[] getErrorMessages() {
        return errorMessages;
    }
}
