package Recources;

/**
 * Questa enumerazione rappresenta lo stato degli elementi che contiene
 * la mappa. Lo stato influisce sulla visibilità degli elementi.
 */
public enum StatusElement {

    VISIBLE,
    PENDING,
    INVISIBLE;

    public static StatusElement convertStatus(String state){
        return switch (state){
            case "VISIBLE" -> StatusElement.VISIBLE;
            case "PENDING" -> StatusElement.PENDING;
            case "INVISIBLE" -> StatusElement.INVISIBLE;
            default -> throw new IllegalStateException("Unexpected value: " + state);
        };
    }

}
