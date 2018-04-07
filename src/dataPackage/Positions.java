package dataPackage;

public enum Positions /** выбор должности */
{
    NONE("Не выбрано"),
    LECTURER("Преподаватель"),
    SENIOR_LECTURER("Старший преподователь"),
    DOCENT("Доцент"),
    PROFESSOR("Профессор");

    private final String text;
    private static final String[] array;

    static {
        array = new String[Positions.values().length];
        for(Positions value : Positions.values()) {
            array[value.ordinal()] = value.toString();
        }
    }

    Positions(final String text) /** установка текста */
    {
        this.text = text;
    }

    @Override
    public String toString()
    {
        return text;
    }

    public static String[] toArray() {
        return array;
    }
}
