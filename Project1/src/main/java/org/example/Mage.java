package org.example;

import java.util.*;

public class Mage implements Comparable {

    private final String name;
    private int level;
    private double power;
    private Set<Mage> apprentices;
    private String compareOptions = "natural";
    private Comparator comparator;

    public Mage(String name, int level, double power, String compareOptions) {
        this.name = name;
        this.level = level;
        this.power = power;
        this.compareOptions = compareOptions;

        if(Objects.equals(compareOptions, "natural")) {
            this.apprentices = new TreeSet<>();
            this.comparator = new NaturalComparator();
        }
        else if(Objects.equals(compareOptions, "alternative")) {
            this.apprentices = new TreeSet<>();
            this.comparator = new AltComparator();
        } else {
            this.apprentices = new HashSet<>();
        }
    }

    public String getName() {
        return name;
    }

    public void AddApprentice(Mage apprentice) {
        this.apprentices.add(apprentice);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Mage))
            return false;
        Mage other = (Mage)o;

        return (this.name == other.name
                && this.level == other.level
                && this.power == other.power
                && this.apprentices == other.apprentices);
    }

    @Override
    public final int hashCode() {
        int result = 17;
        result *= level * power * name.hashCode();

        for(Mage apprentice : apprentices) {
            result += apprentice.hashCode();
        }

        return result;
    }

    public String toString() {
        return this.getName() + " { level=" + this.level
                + " power=" + this.power + " }";
    }
    @Override
    public int compareTo(Object o) {
        return comparator.compare((Object)this, o);
    }

    public Set<Mage> GetApprentices() {
        Set<Mage> apprenticesCopy;
        if(compareOptions == "natural" || compareOptions == "alternative") {
            apprenticesCopy = new TreeSet<>();
        } else {
            apprenticesCopy = new HashSet<>();
        }

        apprenticesCopy.addAll(apprentices);
        return apprenticesCopy;
    }

    public void PrintApprentices(String indent) {
        System.out.println(indent + this.toString());
        for(Mage m : apprentices) {
            m.PrintApprentices(indent + "-");
        }
    }

    public int getLevel() {
        return level;
    }

    public double getPower() {
        return power;
    }

    public String getCompareOptions() {
        return compareOptions;
    }

    public int countApprentices() {
        int apprenticesNumber = 0;
        for(Mage m : apprentices) {
            apprenticesNumber ++;
            apprenticesNumber += m.countApprentices();
        }
        return  apprenticesNumber;
    }
}
