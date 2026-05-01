/*
 * Группа элементов заказа в паттерне Composite.
 * Может содержать вложенные узлы и считать общую стоимость.
 */
package ru.ma1ko.cafeteria.composite;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Group implements Node {
    private final String name;
    private final List<Node> nodeList;

    public Group(String name) {
        this.name = Objects.requireNonNull(name, "name");
        this.nodeList = new ArrayList<>();
    }

    public void add(Node node) {
        nodeList.add(Objects.requireNonNull(node, "node"));
    }

    public void clear() {
        nodeList.clear();
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public BigDecimal cost() {
        BigDecimal total = BigDecimal.ZERO;
        for (Node node : nodeList) {
            total = total.add(node.cost());
        }
        return total;
    }

    @Override
    public int count() {
        int total = 0;
        for (Node node : nodeList) {
            total += node.count();
        }
        return total;
    }

    @Override
    public String text(String prefix) {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix).append(name);
        if (nodeList.isEmpty()) {
            return sb.toString();
        }
        for (Node node : nodeList) {
            sb.append('\n').append(node.text(prefix + "  "));
        }
        return sb.toString();
    }
}
