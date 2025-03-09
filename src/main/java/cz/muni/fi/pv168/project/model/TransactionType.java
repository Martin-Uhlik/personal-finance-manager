package cz.muni.fi.pv168.project.model;

/**
 * @author Martin Uhlik
 */
public enum TransactionType {
    INCOME {
        @Override
        public String toString() {
            return "Income";
        }
    },
    OUTCOME {
        @Override
        public String toString() {
            return "Outcome";
        }
    }
}
