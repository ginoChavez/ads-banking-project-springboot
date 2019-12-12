package banking.persons.domain.enumeration;

public enum PersonStatus {
	ACTIVE(1), INACTIVE(0);
	private final int value;

    private PersonStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
