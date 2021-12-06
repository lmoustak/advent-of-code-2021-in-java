import java.util.Objects;

public class Fish {
    public int dayOfBirth;
    public int gestationDays = 8;

    public Fish(int dayOfBirth, int gestationDays) {
        this.dayOfBirth = dayOfBirth;
        this.gestationDays = gestationDays;
    }

    public Fish(int dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayOfBirth, gestationDays);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Fish other = (Fish) obj;
        return dayOfBirth == other.dayOfBirth && gestationDays == other.gestationDays;
    }

}
