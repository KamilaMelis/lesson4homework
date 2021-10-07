package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 1000;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static String[] heroesAttackTypes = {"Physical", "Magical", "Kinetic", " Medic", "Thor", "Lucky", "Berserk", "Golem"};
    public static int[] heroesHealth = {290, 280, 250, 350, 300, 300, 300, 400};
    public static int[] heroesDamages = {20, 25, 15, 0, 30, 25, 30, 10};
    public static int roundNumber = 0;

    public static void chooseDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackTypes.length); // 0, 1, 2
        bossDefenceType = heroesAttackTypes[randomIndex];
        System.out.println("Boss chose: " + bossDefenceType);
    }

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void round() {
        roundNumber++;
        chooseDefence();
        bossHits();
        heroesHit();
        superAbilityThor();
        superAbilityOfMedic();
        abilityOfLucky();
        abilityOfBerserk();
        abilityOfGolem();
        printStatistics();
        setBossDamage();
        setLuckyHealth();


    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 &&
                heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
    }

    public static void printStatistics() {
        System.out.println("________ ROUND " + roundNumber);
        System.out.println("Boss health: " + bossHealth +
                " [" + bossDamage + "]");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackTypes[i] +
                    " health: " + heroesHealth[i] +
                    " [" + heroesDamages[i] + "]");
        }
        System.out.println("________________");
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) { // i = i + 1
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamages.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                if (bossDefenceType == heroesAttackTypes[i]) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2; //2,3,4,5,6,7,8,9,10
                    if (bossHealth - heroesDamages[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamages[i] * coeff;
                    }
                    System.out.println("Critical damage: "
                            + heroesDamages[i] * coeff);
                } else {
                    if (bossHealth - heroesDamages[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamages[i];
                    }
                }
            }
        }
    }

    public static void superAbilityOfMedic() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && heroesHealth[i] < 100) {
                heroesHealth[i] = heroesHealth[i] + 30;
            }

        }
    }

    public static void superAbilityThor() {
        Random thorOne = new Random();
        boolean thor = thorOne.nextBoolean();
        if (thor == true) {
            bossDamage = 0;
            System.out.println("true");
        } else if (thor == false) {
            bossDamage = 50;
            System.out.println("false");
        }
    }

    public static void setBossDamage() {
        bossDamage = 50;
    }

    public static void abilityOfLucky() {
        Random luckyOne = new Random();
        boolean lucky = luckyOne.nextBoolean();
        if (lucky == true) {
            heroesHealth[5] = 0;
        } else if (lucky == false) {
            heroesHealth[5] = 300;

        }

    }

    public static void setLuckyHealth() {
        heroesHealth[5] = 300;
    }

    public static void abilityOfBerserk() {
        Random berserkOne = new Random();
        int berserk = berserkOne.nextInt(25) + 1;
        if (heroesHealth[6] > 0) {
            heroesHealth[6] = (heroesHealth[6] - bossDamage) + berserk;
            heroesDamages[6] = heroesDamages[6] + berserk;
            System.out.println("Berserk activated SUPER");

        }
        if (heroesHealth[6] < 0) {
            heroesHealth[6] = 0;

        }
    }

    public static boolean abilityOfGolem() {
        boolean protect = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[7] - bossDamage < 0) {
                heroesHealth[7] = 0;
                protect = false;
                break;
            } else if (heroesHealth[7] <= 0 && heroesHealth[i] <= 0) {
                protect = false;
                break;
            } else if (heroesHealth[7] - (bossDamage / 5) > 0 && heroesHealth[i] < 0) {
                protect = false;
                break;
            } else if (heroesHealth[7] > 0) {
                heroesHealth[7] = heroesHealth[7] - bossDamage / 5;
                heroesHealth[i] = heroesHealth[i] + bossDamage / 5;
                protect = true;
                break;
            } else {
                protect = false;
                break;
            }

        }
        return protect;
    }
}
