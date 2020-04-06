public enum Predicate implements PredicateType {
    WALL_IN_FRONT;

    @Override
    public boolean evaluate() {
        return false;
    }
}
