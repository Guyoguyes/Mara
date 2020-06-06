package models;

public class Ranger {
    private String name;
    private int budgeNo;

    public Ranger(String name, int budgeNo){
        this.name = name;
        this.budgeNo = budgeNo;
    }

    public String getName() {
        return name;
    }

    public int getBudgeNo() {
        return budgeNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ranger ranger = (Ranger) o;

        if (budgeNo != ranger.budgeNo) return false;
        return name.equals(ranger.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + budgeNo;
        return result;
    }
}
