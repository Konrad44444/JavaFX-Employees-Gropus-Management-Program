package zad.employees;

public enum EmployeeCondition {
    OBECNY {
        @Override
        public String toString() {
            return "Obecny";
        }
    },
    DELEGACJA {
        @Override
        public String toString() {
            return "Delegacja";
        }
    },
    CHORY {
        @Override
        public String toString() {
            return "Chory";
        }
    },
    NIEOBECNY {
        @Override
        public String toString() {
            return "Nieobecny";
        }
    }
}
