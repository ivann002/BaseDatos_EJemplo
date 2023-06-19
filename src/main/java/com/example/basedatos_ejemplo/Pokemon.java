package com.example.basedatos_ejemplo;

import javafx.beans.property.*;
public class Pokemon {
    private final IntegerProperty IDpoke;
    private final StringProperty pokename;
    private final IntegerProperty HP;
    private final IntegerProperty attack;
    private final IntegerProperty defense;
    private final IntegerProperty spattack;
    private final IntegerProperty spdefense;
    private final IntegerProperty speed;
    private final IntegerProperty dualtype;

    public int getIDpoke() {
        return IDpoke.get();
    }

    public IntegerProperty IDpokeProperty() {
        return IDpoke;
    }

    public void setIDpoke(int IDpoke) {
        this.IDpoke.set(IDpoke);
    }

    public String getPokename() {
        return pokename.get();
    }

    public StringProperty pokenameProperty() {
        return pokename;
    }

    public void setPokename(String pokename) {
        this.pokename.set(pokename);
    }

    public int getHP() {
        return HP.get();
    }

    public IntegerProperty HPProperty() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP.set(HP);
    }

    public int getAttack() {
        return attack.get();
    }

    public IntegerProperty attackProperty() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack.set(attack);
    }

    public int getDefense() {
        return defense.get();
    }

    public IntegerProperty defenseProperty() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense.set(defense);
    }

    public int getSpattack() {
        return spattack.get();
    }

    public IntegerProperty spattackProperty() {
        return spattack;
    }

    public void setSpattack(int spattack) {
        this.spattack.set(spattack);
    }

    public int getSpdefense() {
        return spdefense.get();
    }

    public IntegerProperty spdefenseProperty() {
        return spdefense;
    }

    public void setSpdefense(int spdefense) {
        this.spdefense.set(spdefense);
    }

    public int getSpeed() {
        return speed.get();
    }

    public IntegerProperty speedProperty() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed.set(speed);
    }

    public int getDualtype() {
        return dualtype.get();
    }

    public IntegerProperty dualtypeProperty() {
        return dualtype;
    }

    public void setDualtype(int dualtype) {
        this.dualtype.set(dualtype);
    }
/**
 * Crea un nuevo objeto Pokemon con los valores especificados.
 *
 * @param IDpoke    El ID del Pokemon.
 * @param pokename  El nombre del Pokemon.
 * @param HP        Los puntos de vida del Pokemon.
 * @param attack    El valor de ataque del Pokemon.
 * @param defense   El valor de defensa del Pokemon.
 * @param spattack  El valor de ataque especial del Pokemon.
 * @param spdefense El valor de defensa especial del Pokemon.
 * @param speed     La velocidad del Pokemon.
 * @param dualtype  Si el pokemon tiene tipo dual.
 */
    public Pokemon(Integer IDpoke, String pokename, Integer HP, Integer attack, Integer defense,
                   Integer spattack, Integer spdefense, Integer speed, Integer dualtype){
        this.IDpoke = new SimpleIntegerProperty(IDpoke);
        this.pokename = new SimpleStringProperty(pokename);
        this.HP = new SimpleIntegerProperty(HP);
        this.attack = new SimpleIntegerProperty(attack);
        this.defense = new SimpleIntegerProperty(defense);
        this.spattack = new SimpleIntegerProperty(spattack);
        this.spdefense = new SimpleIntegerProperty(spdefense);
        this.speed = new SimpleIntegerProperty(speed);
        this.dualtype = new SimpleIntegerProperty(dualtype);
    }

    @Override
    public  String toString(){
        return "Pokemon{" +
                ", IDpoke=" + IDpoke +
                ", pokename=" + pokename +
                ", HP=" + HP +
                ", attack=" + attack +
                ", defense=" + defense +
                ", spattack=" + spattack +
                ", spdefense=" + spdefense +
                ", speed=" + speed +
                ", dualtype=" + dualtype +
                '}';
    }
}
