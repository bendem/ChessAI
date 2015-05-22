package be.bendem.chess.utils.timer;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

public enum Part {
    RankMove,
    GenerateMove,
    Move(GenerateMove, RankMove),
    UpdateUI,
    Init,
    Game(Move, UpdateUI, Init),
    Log,
    Idle,
    ;

    static {
        for(Part part : Part.values()) {
            part.parent = Arrays.stream(Part.values())
                .filter(p -> p.hasChildren(part, false))
                .findAny();
        }
    }

    private Set<Integer> children;
    private Optional<Part> parent;

    Part() {
        children = Collections.emptySet();
    }

    Part(Part... children) {
        Set<Integer> set = new HashSet<>(children.length);
        for(Part child : children) {
            set.add(child.ordinal());
        }
        this.children = Collections.unmodifiableSet(set);
    }

    public Part parent() {
        return parent.get();
    }

    public boolean hasParent() {
        return parent.isPresent();
    }

    public boolean hasChildren() {
        return !children.isEmpty();
    }

    public boolean hasChildren(Part part) {
        return hasChildren(part, true);
    }

    public boolean hasChildren(Part part, boolean deep) {
        Predicate<Part> predicate = deep
            ? child -> child.hasChildren(part)
            : child -> child == part;

        return children.contains(part.ordinal()) || children.stream()
            .map(i -> Part.values()[i])
            .filter(predicate)
            .findAny()
            .isPresent();
    }

    public Stream<Part> children() {
        return children.stream().map(i -> Part.values()[i]);
    }

}
