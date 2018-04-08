package dataPackage;

public enum Positions /** выбор должности */
{
    NONE("Не выбрано"),
    LECTURER("Преподаватель"),
    SENIOR_LECTURER("Старший преподователь"),
    DOCENT("Доцент"),
    PROFESSOR("Профессор");

    private final String text;
    
    Positions(final String text) /** установка текста */
    {
        this.text = text;
    }

    @Override
    public String toString()
    {
        return text;
    }

}
