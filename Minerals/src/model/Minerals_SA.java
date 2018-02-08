package model;

import java.util.Comparator;
import java.util.LinkedList;

/**
 * Insert class description here
 *
 * @author Lukas
 * @version Feb 4, 2018
 */
public class Minerals_SA {

    private final int totalMiners;
    private final int goldMiners;
    private final int silverMiners;
    private final int copperMiners;
    private final int jokerMiners;
    private final int goldChargeCapacity;
    private final int silverChargeCapacity;
    private final int copperChargeCapacity;
    private final String chargeUnit;
    private final int goldGain;
    private final int silverGain;
    private final int copperGain;
    private LinkedList<Mine> mines;
    private LinkedList<Worker> workers;
    private LinkedList<LinkedList<Profit>> maxGains;

    public Minerals_SA() {
        this.totalMiners = 0;
        this.goldMiners = 0;
        this.silverMiners = 0;
        this.copperMiners = 0;
        this.jokerMiners = 0;
        this.goldChargeCapacity = 0;
        this.silverChargeCapacity = 0;
        this.copperChargeCapacity = 0;
        this.chargeUnit = "";
        this.goldGain = 0;
        this.silverGain = 0;
        this.copperGain = 0;
        this.mines = new LinkedList<>();
        this.workers = new LinkedList<>();
        this.maxGains = new LinkedList<>();
    }

    public Minerals_SA(int totalMiners, int goldMiners, int silverMiners, int copperMiners, int jokerMiners, int capacityGold, int capacitySilver, int capacityCopper, String chargeUnit, int goldGain, int silverGain, int copperGain) {
        this.totalMiners = totalMiners;
        this.goldMiners = goldMiners;
        this.silverMiners = silverMiners;
        this.copperMiners = copperMiners;
        this.jokerMiners = jokerMiners;
        this.goldChargeCapacity = capacityGold;
        this.silverChargeCapacity = capacitySilver;
        this.copperChargeCapacity = capacityCopper;
        this.chargeUnit = chargeUnit;
        this.goldGain = goldGain;
        this.silverGain = silverGain;
        this.copperGain = copperGain;
        this.mines = new LinkedList<>();
        this.workers = new LinkedList<>();
        this.maxGains = new LinkedList<>();
    }
    

    /**
     * @return the totalMiners
     */
    public int getTotalMiners() {
        return totalMiners;
    }

    /**
     * @return the goldMiners
     */
    public int getGoldMiners() {
        return goldMiners;
    }

    /**
     * @return the silverMiners
     */
    public int getSilverMiners() {
        return silverMiners;
    }

    /**
     * @return the copperMiners
     */
    public int getCopperMiners() {
        return copperMiners;
    }

    /**
     * @return the jokerMiners
     */
    public int getJokerMiners() {
        return jokerMiners;
    }

    /**
     * @return the goldChargeCapacity
     */
    public int getGoldChargeCapacity() {
        return goldChargeCapacity;
    }

    /**
     * @return the silverChargeCapacity
     */
    public int getSilverChargeCapacity() {
        return silverChargeCapacity;
    }

    /**
     * @return the copperChargeCapacity
     */
    public int getCopperChargeCapacity() {
        return copperChargeCapacity;
    }

    /**
     * @return the chargeUnit
     */
    public String getChargeUnit() {
        return chargeUnit;
    }

    /**
     * @return the goldGain
     */
    public int getGoldGain() {
        return goldGain;
    }

    /**
     * @return the silverGain
     */
    public int getSilverGain() {
        return silverGain;
    }

    /**
     * @return the copperGain
     */
    public int getCopperGain() {
        return copperGain;
    }

    /**
     * @return the mines
     */
    public LinkedList<Mine> getMines() {
        return mines;
    }

    /**
     * @param mines the mines to set
     */
    public void setMines(LinkedList<Mine> mines) {
        this.mines = mines;
    }

    /**
     * @param workers the workers to set
     */
    public void setWorkers(LinkedList<Worker> workers) {
        this.workers = workers;
    }

    /**
     * @return the workers
     */
    public LinkedList<Worker> getWorkers() {
        return workers;
    }

    /**
     * Calcula todas las posibles ganancias dependiendo del mineral
     *
     */
    public void allPossibleProfits() {
        if (getMines().size() > 0) {
            for (Mine mine : getMines()) {
                if (mine.getMineral().equalsIgnoreCase("oro")) {
                    allProfits(mine, getGoldGain());
                } else if (mine.getMineral().equalsIgnoreCase("plata")) {
                    allProfits(mine, getSilverGain());
                } else if (mine.getMineral().equalsIgnoreCase("cobre")) {
                    allProfits(mine, getCopperGain());
                }
            }
        }
    }

    /**
     * En base a los parámetros datos, calcula todas las ganancias para una mina
     * dada según las restricciones del problema
     *
     * @param mine
     * @param gain
     */
    public void allProfits(Mine mine, int gain) {
        double profit = 0;
        int mineros = 0;
        int depositsInMine = mine.depositsPerType(2);
        int baseProfit = depositsInMine * mine.getDepositCapacity() * gain;
        System.out.println("Mina: " + getMines().indexOf(mine) + " Depositos: " + depositsInMine + " Base: " + baseProfit);
        for (int i = 1; i <= mine.getMinersCapacity(); i++) {
            mineros = i;
            //System.out.println(mineros);
            if (i < depositsInMine) {
                int j = 0;
                while (mineros >= 0) {
                    profit = baseProfit + (800 * mineros) + ((800 * 0.7) * j);
                    Profit currentProfit = new Profit(profit, mineros, j, mine.getMineral());
                    mine.getProfits().add(currentProfit);
                    j++;
                    mineros--;
                }
            } else if (i == depositsInMine) {
                int j = 0;
                while (mineros >= 0) {
                    profit = baseProfit + (950 * mineros) + ((950 * 0.7) * j);
                    Profit currentProfit = new Profit(profit, mineros, j, mine.getMineral());
                    mine.getProfits().add(currentProfit);
                    j++;
                    mineros--;
                }
            } else if (i > depositsInMine) {
                int j = 0;
                while (mineros >= 0) {
                    profit = baseProfit + ((250 * mineros) + ((250 * 0.7) * j)) - (130 * ((mineros + j) - depositsInMine));
                    Profit currentProfit = new Profit(profit, mineros, j, mine.getMineral());
                    mine.getProfits().add(currentProfit);
                    j++;
                    mineros--;
                }
            }
        }
        Comparator<Profit> comparator = (profit1, profit2) -> {
            return profit1.getQuantity().compareTo(profit2.getQuantity());
        };
        mine.getProfits().sort(comparator.reversed());
    }

    /**
     * Descripción del método.
     *
     */
    public void calculateMaximumPossibleMiners() {
        int maxTotalMiners;
        int maxGoldMiners;
        int goldMinersJobless = 0;
        int goldJokers = 0;
        int maxSilverMiners;
        int silverMinersJobless = 0;
        int silverJokers = 0;
        int maxCopperMiners;
        int copperMinersJobless = 0;
        int copperJokers = 0;
        int minersJokers = getJokerMiners();
        int maxJokersMiners;
        int goldMinersTemp = 0;
        int silverMinersTemp = 0;
        int copperMinersTemp = 0;
        LinkedList<Integer> goldMinersPerMine = new LinkedList<>();
        LinkedList<Integer> silverMinersPerMine = new LinkedList<>();
        LinkedList<Integer> copperMinersPerMine = new LinkedList<>();

        if (getMines().size() > 0) {
            for (Mine mine : getMines()) {
                if (mine.getMineral().equalsIgnoreCase("oro")) {
                    goldMinersPerMine.add(mine.getMinersCapacity());
                } else if (mine.getMineral().equalsIgnoreCase("plata")) {
                    silverMinersPerMine.add(mine.getMinersCapacity());
                } else if (mine.getMineral().equalsIgnoreCase("cobre")) {
                    copperMinersPerMine.add(mine.getMinersCapacity());
                }
            }

            if (!goldMinersPerMine.isEmpty()) {
                goldMinersTemp = goldMinersPerMine.stream().mapToInt(i -> i).sum();
                if (goldMinersTemp < getGoldMiners()) {
                    goldMinersJobless = getGoldMiners() - goldMinersTemp;
                } else if (goldMinersTemp > getGoldMiners()) {
                    goldJokers = goldMinersTemp - getGoldMiners();
                    minersJokers = minersJokers - goldJokers;
                }
            }

            if (!silverMinersPerMine.isEmpty()) {
                silverMinersTemp = silverMinersPerMine.stream().mapToInt(i -> i).sum();
                if (silverMinersTemp < getSilverMiners()) {
                    silverMinersJobless = getSilverMiners() - silverMinersTemp;
                } else if (silverMinersTemp > getSilverMiners()) {
                    silverJokers = silverMinersTemp - getSilverMiners();
                    minersJokers = minersJokers - silverJokers;
                }
            }

            if (!copperMinersPerMine.isEmpty()) {
                copperMinersTemp = copperMinersPerMine.stream().mapToInt(i -> i).sum();
                if (copperMinersTemp < getCopperMiners()) {
                    copperMinersJobless = getCopperMiners() - copperMinersTemp;
                } else if (copperMinersTemp > getCopperMiners()) {
                    copperJokers = copperMinersTemp - getCopperMiners();
                    minersJokers = minersJokers - copperJokers;
                }
            }

            maxGoldMiners = maxGoldMiners(goldMinersJobless, goldJokers);
            maxSilverMiners = maxSilverMiners(silverMinersJobless, silverJokers);
            maxCopperMiners = maxCopperMiners(copperMinersJobless, copperJokers);
            maxJokersMiners = maxJokerMiners(minersJokers);
            System.out.println("Gold: " + maxGoldMiners);
            System.out.println("Silver: " + maxSilverMiners);
            System.out.println("Copper: " + maxCopperMiners);
            System.out.println("Jokers: " + maxJokersMiners);
            maxTotalMiners = maxGoldMiners + maxSilverMiners + maxCopperMiners;

            if (maxTotalMiners < getTotalMiners()) {
                findMaximumProfits(maxTotalMiners, goldMinersPerMine, silverMinersPerMine, copperMinersPerMine, maxJokersMiners);
            } else if (getTotalMiners() <= maxTotalMiners) {
                findMaximumProfits(getTotalMiners(), goldMinersPerMine, silverMinersPerMine, copperMinersPerMine, maxJokersMiners);
            }
        }
    }

    /**
     * Descripción del método.
     *
     * @param minersJokers
     * @return
     */
    public int maxJokerMiners(int minersJokers) {
        System.out.println("Jokers: " + minersJokers);
        int maxJokersMiners = 0;
        if (minersJokers > 0) {
            maxJokersMiners = getJokerMiners() - minersJokers;
        } else if (minersJokers <= 0) {
            maxJokersMiners = getJokerMiners();
        }
        return maxJokersMiners;
    }

    /**
     * Descripción del método.
     *
     * @param goldMinersJobless
     * @param goldJokers
     * @return
     */
    public int maxGoldMiners(int goldMinersJobless, int goldJokers) {
        System.out.println("goldJobless: " + goldMinersJobless + " goldJokers: " + goldJokers);
        int maxGoldMiners = 0;
        if (goldMinersJobless == 0 && goldJokers == 0) {
            maxGoldMiners = getGoldMiners();
        } else if (goldMinersJobless > 0) {
            maxGoldMiners = getGoldMiners() - goldMinersJobless;
        } else if (goldJokers > 0) {
            maxGoldMiners = getGoldMiners() + goldJokers;
        }
        return maxGoldMiners;
    }

    /**
     * Descripción del método.
     *
     * @param silverdMinersJobless
     * @param silverJokers
     * @return
     */
    public int maxSilverMiners(int silverdMinersJobless, int silverJokers) {
        System.out.println("silverJobless: " + silverdMinersJobless + " silverJokers: " + silverJokers);
        int maxSilverMiners = 0;
        if (silverdMinersJobless == 0 && silverJokers == 0) {
            maxSilverMiners = getSilverMiners();
        } else if (silverdMinersJobless > 0) {
            maxSilverMiners = getSilverMiners() - silverdMinersJobless;
        } else if (silverJokers > 0) {
            maxSilverMiners = getSilverMiners() + silverJokers;
        }
        return maxSilverMiners;
    }

    /**
     * Descripción del método.
     *
     * @param copperMinersJobless
     * @param copperJokers
     * @return
     */
    public int maxCopperMiners(int copperMinersJobless, int copperJokers) {
        System.out.println("copperJobless: " + copperMinersJobless + " copperJokers: " + copperJokers);
        int maxCopperMiners = 0;
        if (copperMinersJobless == 0 && copperJokers == 0) {
            maxCopperMiners = getCopperMiners();
        } else if (copperMinersJobless > 0) {
            maxCopperMiners = getCopperMiners() - copperMinersJobless;
        } else if (copperJokers > 0) {
            maxCopperMiners = getCopperMiners() + copperJokers;
        }
        return maxCopperMiners;
    }

    /**
     * Descripción del método.
     *
     * @param maxTotalMiners
     * @param maxGoldMiners
     * @param maxSilverMiners
     * @param maxCopperMiners
     * @param maxJokerMiners
     */
    public void maximumGain(int maxTotalMiners, LinkedList<Integer> maxGoldMiners, LinkedList<Integer> maxSilverMiners, LinkedList<Integer> maxCopperMiners, int maxJokerMiners) {
        System.out.println("Máximos mineros totales: " + maxTotalMiners + " Máximos mineros oro: " + maxGoldMiners.stream().mapToInt(i -> i).sum() + " Máximos mineros plata: " + maxSilverMiners.stream().mapToInt(i -> i).sum() + " Máximos mineros cobre: " + maxCopperMiners.stream().mapToInt(i -> i).sum() + " Máximos mineros comodines: " + maxJokerMiners);
        //orderMinesByHighestProfit();
        String maxGold = "";
        for (Integer maxGoldMiner : maxGoldMiners) {
            maxGold = maxGold + maxGoldMiner + "-";
        }
        String maxSilver = "";
        for (Integer maxSilverMiner : maxSilverMiners) {
            maxSilver = maxSilver + maxSilverMiner + "-";
        }
        String maxCopper = "";
        for (Integer maxCopperMiner : maxGoldMiners) {
            maxCopper = maxCopper + maxCopperMiner + "-";
        }
        System.out.println("Minas oro: " + maxGold);
        System.out.println("Minas plata: " + maxSilver);
        System.out.println("Minas cobre: " + maxCopper);

        LinkedList<Profit> possibleMaxGain = new LinkedList<>();
        int stage = 0;
        for (int i = 0; i < getMines().size(); i++) {
            System.out.println("Mina " + i + ": " + getMines().get(i).toString());
        }
        LinkedList<Integer> copyMaxGoldMiners = (LinkedList<Integer>) maxGoldMiners.clone();
        LinkedList<Integer> copyMaxSilverMiners = (LinkedList<Integer>) maxSilverMiners.clone();
        LinkedList<Integer> copyMaxCopperMiners = (LinkedList<Integer>) maxCopperMiners.clone();
        boolean found = false;
        while (!found) {
            LinkedList<Profit> gainOption = findMaximumGainOption(maxTotalMiners, (LinkedList<Integer>) maxGoldMiners.clone(), (LinkedList<Integer>) maxSilverMiners.clone(), (LinkedList<Integer>) maxCopperMiners.clone(), maxJokerMiners, possibleMaxGain, stage);
//        System.out.println(gainOption.size());
//        for (Profit profit : gainOption) {
//            System.out.println(profit.toString());
//        }
            getMaxGains().add(gainOption);
            maxGainFound(gainOption, maxTotalMiners, copyMaxGoldMiners.stream().mapToInt(i -> i).sum(), copyMaxSilverMiners.stream().mapToInt(i -> i).sum(), copyMaxCopperMiners.stream().mapToInt(i -> i).sum(), maxJokerMiners);
            for (Profit profit : getMaxGains().getLast()) {
                System.out.println(profit.toString());
            }
        }

//        System.out.println("Ganancia maxima encontrada");
//        for (LinkedList<Profit> maxGain : getMaxGains()) {
//            for (Profit profit : maxGain) {
//                System.out.println(profit.toString());
//            }
//        }
    }

    /**
     * Descripción del método.
     *
     * @param maxTotalMiners
     * @param maxGoldMiners
     * @param maxSilverMiners
     * @param maxCopperMiners
     * @param maxJokerMiners
     */
    public void findMaximumProfits(int maxTotalMiners, LinkedList<Integer> maxGoldMiners, LinkedList<Integer> maxSilverMiners, LinkedList<Integer> maxCopperMiners, int maxJokerMiners) {
        System.out.println("Máximos mineros totales: " + maxTotalMiners + " Máximos mineros oro: " + maxGoldMiners.stream().mapToInt(i -> i).sum() + " Máximos mineros plata: " + maxSilverMiners.stream().mapToInt(i -> i).sum() + " Máximos mineros cobre: " + maxCopperMiners.stream().mapToInt(i -> i).sum() + " Máximos mineros comodines: " + maxJokerMiners);
        //orderMinesByHighestProfit();
        String maxGold = "";
        for (Integer maxGoldMiner : maxGoldMiners) {
            maxGold = maxGold + maxGoldMiner + "-";
        }
        String maxSilver = "";
        for (Integer maxSilverMiner : maxSilverMiners) {
            maxSilver = maxSilver + maxSilverMiner + "-";
        }
        String maxCopper = "";
        for (Integer maxCopperMiner : maxGoldMiners) {
            maxCopper = maxCopper + maxCopperMiner + "-";
        }
        System.out.println("Minas oro: " + maxGold);
        System.out.println("Minas plata: " + maxSilver);
        System.out.println("Minas cobre: " + maxCopper);

        LinkedList<Profit> gainCandidates;
        LinkedList<Profit> possiblePath = new LinkedList<>();
        int stage = 0;
        for (int i = 0; i < getMines().size(); i++) {
            System.out.println("Mina " + i + ": " + getMines().get(i).toString());
        }

        gainCandidates = findMatchingProfits(getMines().get(stage), maxGoldMiners, maxSilverMiners, maxCopperMiners);
//        for (Profit gainCandidate : gainCandidates) {
//            System.out.println(gainCandidate.toString());
//        }
        //int possibleGains = countPossibleMatchingProfits(maxGoldMiners, maxSilverMiners, maxCopperMiners);
        for (int i = 0; i < gainCandidates.size(); i++) {
            LinkedList<Profit> MaxProfit = profitsMaximum(maxTotalMiners, (LinkedList<Integer>) maxGoldMiners.clone(), (LinkedList<Integer>) maxSilverMiners.clone(), (LinkedList<Integer>) maxCopperMiners.clone(), maxJokerMiners, stage, possiblePath, gainCandidates);
            if (MaxProfit != null) {
                verifiedMaxProfit(MaxProfit);
//                verifiedMaxProfit(MaxProfit, maxGoldMiners, maxSilverMiners, maxCopperMiners, maxJokerMiners);
                System.out.println("Max profit: " + MaxProfit.size());
                if (!getMaxGains().contains(MaxProfit)) {
                    System.out.println("Nueva max profit");
                    getMaxGains().add(MaxProfit);
                }
            }
        }
        for (LinkedList<Profit> maxGain : getMaxGains()) {
            System.out.println("----- Maxmima ganancia -----");
            for (Profit profit : maxGain) {
                System.out.println(profit.toString());
            }
            System.out.println("----- Fin -----");
        }

    }

    private boolean verifiedMaxProfit(LinkedList<Profit> MaxProfit) {
        int currentGold = 0;
        int currentGoldJokers = 0;
//        int goldMinersJobless = 0;
//        int goldJokers = 0;
        int currentSilver = 0;
        int currentSilverJokers = 0;
//        int silverMinersJobless = 0;
//        int silverJokers = 0;
        int currentCopper = 0;
        int currentCopperJokers = 0;
//        int copperMinersJobless = 0;
//        int copperJokers = 0;
        int minersJokers = 0;

        for (Profit profit : MaxProfit) {
            if (profit.getMineral().equalsIgnoreCase("oro")) {
                currentGold = currentGold + profit.getSpecialists();
                currentGoldJokers = currentGoldJokers + profit.getJokers();
            } else if (profit.getMineral().equalsIgnoreCase("plata")) {
                currentSilver = currentSilver + profit.getSpecialists();
                currentSilverJokers = currentSilverJokers + profit.getJokers();
            } else if (profit.getMineral().equalsIgnoreCase("cobre")) {
                currentCopper = currentCopper + profit.getSpecialists();
                currentCopperJokers = currentCopperJokers + profit.getJokers();
            }
        }

//        System.out.println("goldJobless: " + goldMinersJobless + " goldJokers: " + goldJokers);
//        System.out.println("silverJobless: " + silverMinersJobless + " silverJokers: " + silverJokers);
//        System.out.println("copperJobless: " + copperMinersJobless + " copperJokers: " + copperJokers);
        System.out.println("Current gold: " + currentGold + " goldJokers: " + currentGoldJokers);
        System.out.println("Current silver: " + currentSilver + " silverJokers: " + currentSilverJokers);
        System.out.println("Current copper: " + currentCopper + " copperJokers: " + currentCopperJokers);
        minersJokers = currentGoldJokers + currentSilverJokers + currentCopperJokers;
        if ((currentGold + currentGoldJokers) == getGoldMiners() && (currentSilver + currentSilverJokers) == getSilverMiners() && (currentCopper + currentCopperJokers) == getCopperMiners() && minersJokers == getJokerMiners()) {
            System.out.println("Max gain sirve");
            return true;
        } else {
            return false;
        }
    }

    /**
     * Descripción del método.
     *
     * @param stage
     * @param profitToValidate
     * @param maxSpecialists
     * @param maxJokers
     * @return descripción de lo que retorna
     */
    public boolean validateProfitInMine(int stage, Profit profitToValidate, LinkedList<Integer> maxSpecialists, int maxJokers) {
        if (maxJokers > 0) {
            if (profitToValidate.getMineral().equalsIgnoreCase("oro")) {
                if (maxSpecialists.stream().mapToInt(i -> i).sum() > getGoldMiners()) {
                    if (profitToValidate.getJokers() == 0) {
                        return false;
                    }
                }
            } else if (profitToValidate.getMineral().equalsIgnoreCase("plata")) {
                if (maxSpecialists.stream().mapToInt(i -> i).sum() > getSilverMiners()) {
                    if (profitToValidate.getJokers() == 0) {
                        return false;
                    }
                }
            } else if (profitToValidate.getMineral().equalsIgnoreCase("cobre")) {
                if (maxSpecialists.stream().mapToInt(i -> i).sum() > getCopperMiners()) {
                    if (profitToValidate.getJokers() == 0) {
                        return false;
                    }
                }
            }
        } else if (profitToValidate.getJokers() == 0) {
            return true;
        }
        return true;
    }

    private LinkedList<Profit> findMatchingProfits(Mine mine, LinkedList<Integer> maxGoldMiners, LinkedList<Integer> maxSilverMiners, LinkedList<Integer> maxCopperMiners) {
        LinkedList<Profit> matchingProfits = new LinkedList<>();
        if (mine != null) {
            if (mine.getMineral().equalsIgnoreCase("oro")) {
                for (Integer maxGoldMiner : maxGoldMiners) {
                    if (mine.getMinersCapacity() == maxGoldMiner) {
                        for (Profit profit : mine.getProfits()) {
                            if (profit.totalMiners() == maxGoldMiner) {
                                matchingProfits.add(profit);
                            }
                        }
                    }
                }
                return matchingProfits;
            } else if (mine.getMineral().equalsIgnoreCase("plata")) {
                for (Integer maxSilverMiner : maxSilverMiners) {
                    if (mine.getMinersCapacity() == maxSilverMiner) {
                        for (Profit profit : mine.getProfits()) {
                            if (profit.totalMiners() == maxSilverMiner) {
                                matchingProfits.add(profit);
                            }
                        }
                    }
                }
                return matchingProfits;
            } else if (mine.getMineral().equalsIgnoreCase("cobre")) {
                for (Integer maxCopperMiner : maxCopperMiners) {
                    if (mine.getMinersCapacity() == maxCopperMiner) {
                        for (Profit profit : mine.getProfits()) {
                            if (profit.totalMiners() == maxCopperMiner) {
                                matchingProfits.add(profit);
                            }
                        }
                    }
                }
                return matchingProfits;
            }
        }
        return null;
    }

    private int countPossibleMatchingProfits(LinkedList<Integer> maxGoldMiners, LinkedList<Integer> maxSilverMiners, LinkedList<Integer> maxCopperMiners) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private LinkedList<Profit> profitsMaximum(int maxTotalMiners, LinkedList<Integer> maxGoldMiners, LinkedList<Integer> maxSilverMiners, LinkedList<Integer> maxCopperMiners, int maxJokerMiners, int stage, LinkedList<Profit> possiblePath, LinkedList<Profit> gainCandidates) {
        if (possiblePath.size() == getMines().size()) {
            if (maxTotalMiners == 0 && maxGoldMiners.isEmpty() && maxSilverMiners.isEmpty() && maxCopperMiners.isEmpty() && maxJokerMiners == 0) {
                if (verifiedMaxProfit(possiblePath)) {
                    return possiblePath;
                } else {
                    Profit p = possiblePath.removeLast();
                    return possiblePath;

                }
            }
        } else if (stage < getMines().size()) {
            for (int i = 0; i < gainCandidates.size(); i++) {
                if (maxTotalMiners > 0) {
                    if (getMines().get(stage).getMineral().equalsIgnoreCase("oro")) {
                        if (getMines().get(stage).getMinersCapacity() <= maxGoldMiners.stream().mapToInt(j -> j).sum()) {
                            for (int j = 0; j < maxGoldMiners.size(); j++) {
                                if (gainCandidates.get(i).totalMiners() == maxGoldMiners.get(j)) {
                                    if (validateProfitInMine(stage, gainCandidates.get(i), maxGoldMiners, maxJokerMiners)) {
                                        possiblePath.add(gainCandidates.get(i));
                                        stage++;
                                        maxGoldMiners.remove((Integer) gainCandidates.get(i).totalMiners());
                                        maxTotalMiners = maxTotalMiners - gainCandidates.get(i).totalMiners();
                                        maxJokerMiners = maxJokerMiners - gainCandidates.get(i).getJokers();
                                        if (possiblePath.size() < getMines().size()) {
                                            LinkedList<Profit> gainCandidatesNextStage = findMatchingProfits(getMines().get(stage), maxGoldMiners, maxSilverMiners, maxCopperMiners);
                                            possiblePath = profitsMaximum(maxTotalMiners, maxGoldMiners, maxSilverMiners, maxCopperMiners, maxJokerMiners, stage, possiblePath, gainCandidatesNextStage);
                                            if (possiblePath.size() == getMines().size()) {
                                                return possiblePath;
                                            } else {
                                                stage--;
                                                possiblePath.removeLast();
                                                maxGoldMiners.addFirst((Integer) gainCandidates.get(i).totalMiners());
                                                maxTotalMiners = maxTotalMiners + gainCandidates.get(i).totalMiners();
                                                maxJokerMiners = maxJokerMiners + gainCandidates.get(i).getJokers();
                                            }
                                        } else if (maxTotalMiners == 0 && maxGoldMiners.isEmpty() && maxSilverMiners.isEmpty() && maxCopperMiners.isEmpty() && maxJokerMiners == 0) {
                                            if (verifiedMaxProfit(possiblePath)) {
                                                return possiblePath;
                                            } else {
                                                stage--;
                                                possiblePath.removeLast();
                                                maxSilverMiners.addFirst((Integer) gainCandidates.get(i).totalMiners());
                                                maxTotalMiners = maxTotalMiners + gainCandidates.get(i).totalMiners();
                                                maxJokerMiners = maxJokerMiners + gainCandidates.get(i).getJokers();
                                            }

                                        }
                                    }
//                                    else {
//                                        stage--;
//                                        possiblePath.removeLast();
//                                        maxGoldMiners.addFirst((Integer) gainCandidates.get(i).totalMiners());
//                                        maxTotalMiners = maxTotalMiners + gainCandidates.get(i).totalMiners();
//                                        maxJokerMiners = maxJokerMiners + gainCandidates.get(i).getJokers();
//                                    }
                                }
                            }
                        }
                    } else if (getMines().get(stage).getMineral().equalsIgnoreCase("plata")) {
                        if (getMines().get(stage).getMinersCapacity() <= maxSilverMiners.stream().mapToInt(j -> j).sum()) {
                            for (int j = 0; j < maxSilverMiners.size(); j++) {
                                if (gainCandidates.get(i).totalMiners() == maxSilverMiners.get(j)) {
                                    if (validateProfitInMine(stage, gainCandidates.get(i), maxSilverMiners, maxJokerMiners)) {
                                        possiblePath.add(gainCandidates.get(i));
                                        stage++;
                                        maxSilverMiners.remove((Integer) gainCandidates.get(i).totalMiners());
                                        maxTotalMiners = maxTotalMiners - gainCandidates.get(i).totalMiners();
                                        maxJokerMiners = maxJokerMiners - gainCandidates.get(i).getJokers();
                                        if (possiblePath.size() < getMines().size()) {
                                            LinkedList<Profit> gainCandidatesNextStage = findMatchingProfits(getMines().get(stage), maxGoldMiners, maxSilverMiners, maxCopperMiners);
                                            possiblePath = profitsMaximum(maxTotalMiners, maxGoldMiners, maxSilverMiners, maxCopperMiners, maxJokerMiners, stage, possiblePath, gainCandidatesNextStage);
                                            if (possiblePath.size() == getMines().size()) {
                                                return possiblePath;
                                            } else {
                                                stage--;
                                                possiblePath.removeLast();
                                                maxSilverMiners.addFirst((Integer) gainCandidates.get(i).totalMiners());
                                                maxTotalMiners = maxTotalMiners + gainCandidates.get(i).totalMiners();
                                                maxJokerMiners = maxJokerMiners + gainCandidates.get(i).getJokers();
                                            }
                                        } else if (maxTotalMiners == 0 && maxGoldMiners.isEmpty() && maxSilverMiners.isEmpty() && maxCopperMiners.isEmpty() && maxJokerMiners == 0) {
                                            if (verifiedMaxProfit(possiblePath)) {
                                                return possiblePath;
                                            } else {
                                                stage--;
                                                possiblePath.removeLast();
                                                maxSilverMiners.addFirst((Integer) gainCandidates.get(i).totalMiners());
                                                maxTotalMiners = maxTotalMiners + gainCandidates.get(i).totalMiners();
                                                maxJokerMiners = maxJokerMiners + gainCandidates.get(i).getJokers();
                                            }

                                        }
                                    }
                                    //else {
//                                        stage--;
//                                        possiblePath.removeLast();
//                                        maxSilverMiners.addFirst((Integer) gainCandidates.get(i).totalMiners());
//                                        maxTotalMiners = maxTotalMiners + gainCandidates.get(i).totalMiners();
//                                        maxJokerMiners = maxJokerMiners + gainCandidates.get(i).getJokers();
//                                    }
                                }
                            }
                        }
                    } else if (getMines().get(stage).getMineral().equalsIgnoreCase("cobre")) {
                        if (getMines().get(stage).getMinersCapacity() <= maxCopperMiners.stream().mapToInt(j -> j).sum()) {
                            for (int j = 0; j < maxCopperMiners.size(); j++) {
                                if (gainCandidates.get(i).totalMiners() == maxCopperMiners.get(j)) {
                                    if (validateProfitInMine(stage, gainCandidates.get(i), maxCopperMiners, maxJokerMiners)) {
                                        possiblePath.add(gainCandidates.get(i));
                                        stage++;
                                        maxCopperMiners.remove((Integer) gainCandidates.get(i).totalMiners());
                                        maxTotalMiners = maxTotalMiners - gainCandidates.get(i).totalMiners();
                                        maxJokerMiners = maxJokerMiners - gainCandidates.get(i).getJokers();
                                        if (possiblePath.size() < getMines().size()) {
                                            LinkedList<Profit> gainCandidatesNextStage = findMatchingProfits(getMines().get(stage), maxGoldMiners, maxSilverMiners, maxCopperMiners);
                                            possiblePath = profitsMaximum(maxTotalMiners, maxGoldMiners, maxSilverMiners, maxCopperMiners, maxJokerMiners, stage, possiblePath, gainCandidatesNextStage);
                                            if (possiblePath.size() == getMines().size()) {
                                                return possiblePath;

                                            } else {
                                                stage--;
                                                possiblePath.removeLast();
                                                maxCopperMiners.addFirst((Integer) gainCandidates.get(i).totalMiners());
                                                maxTotalMiners = maxTotalMiners + gainCandidates.get(i).totalMiners();
                                                maxJokerMiners = maxJokerMiners + gainCandidates.get(i).getJokers();
                                            }
                                        } else if (maxTotalMiners == 0 && maxGoldMiners.isEmpty() && maxSilverMiners.isEmpty() && maxCopperMiners.isEmpty() && maxJokerMiners == 0) {
                                            if (verifiedMaxProfit(possiblePath)) {
                                                return possiblePath;
                                            } else {
                                                stage--;
                                                possiblePath.removeLast();
                                                maxSilverMiners.addFirst((Integer) gainCandidates.get(i).totalMiners());
                                                maxTotalMiners = maxTotalMiners + gainCandidates.get(i).totalMiners();
                                                maxJokerMiners = maxJokerMiners + gainCandidates.get(i).getJokers();
                                            }
                                        }
                                    }
//                                    else {
//                                        stage--;
//                                        possiblePath.removeLast();
//                                        maxCopperMiners.addFirst((Integer) gainCandidates.get(i).totalMiners());
//                                        maxTotalMiners = maxTotalMiners + gainCandidates.get(i).totalMiners();
//                                        maxJokerMiners = maxJokerMiners + gainCandidates.get(i).getJokers();
//                                    }
                                }
                            }
                        }
                    }
                }
//                else if (maxTotalMiners == 0 && maxGoldMiners.isEmpty() && maxSilverMiners.isEmpty() && maxCopperMiners.isEmpty() && maxJokerMiners == 0) {
//                    return possiblePath;
//                } else {
//                    possiblePath.removeLast();
//                    return possiblePath;
//                }
//                if (possiblePath != null) {
//                    return possiblePath;
//                }
            }
            return possiblePath;
        }
        return null;
    }

    public boolean maxGainFound(LinkedList<Profit> gainPath, int maxTotalMiners, int maxGoldMiners, int maxSilverMiners, int maxCopperMiners, int maxJokerMiners) {
        System.out.println("gainPath");
        if (!gainPath.isEmpty()) {
            for (int i = 0; i < getMines().size(); i++) {
                if (getMines().get(i).getMineral().equalsIgnoreCase("oro")) {
                    maxTotalMiners = maxTotalMiners - gainPath.get(i).totalMiners();
                    maxGoldMiners = maxGoldMiners - gainPath.get(i).getSpecialists();
                    maxJokerMiners = maxJokerMiners - gainPath.get(i).getJokers();
                } else if (getMines().get(i).getMineral().equalsIgnoreCase("plata")) {
                    maxTotalMiners = maxTotalMiners - gainPath.get(i).totalMiners();
                    maxSilverMiners = maxSilverMiners - gainPath.get(i).getSpecialists();
                    maxJokerMiners = maxJokerMiners - gainPath.get(i).getJokers();
                } else if (getMines().get(i).getMineral().equalsIgnoreCase("cobre")) {
                    maxTotalMiners = maxTotalMiners - gainPath.get(i).totalMiners();
                    maxCopperMiners = maxCopperMiners - gainPath.get(i).getSpecialists();
                    maxJokerMiners = maxJokerMiners - gainPath.get(i).getJokers();
                }

            }

            if (maxTotalMiners == 0 && maxGoldMiners == 0 && maxSilverMiners == 0 && maxCopperMiners == 0 && maxJokerMiners == 0) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Descripción del método.
     *
     */
    public void orderMinesByHighestProfit() {
        if (!getMines().isEmpty()) {
            Comparator<Mine> comparator = (mine1, mine2) -> {
                return mine1.getProfits().get(0).getQuantity().compareTo(mine2.getProfits().get(0).getQuantity());
            };
            getMines().sort(comparator.reversed());
        }
    }

    private LinkedList findMaximumGainOption(int maxTotalMiners, LinkedList<Integer> maxGoldMiners, LinkedList<Integer> maxSilverMiners, LinkedList<Integer> maxCopperMiners, int maxJokersMiners, LinkedList<Profit> possibleMaxGain, int stage) {
        System.out.println("Máximos mineros totales: " + maxTotalMiners + " Máximos mineros oro: " + maxGoldMiners.stream().mapToInt(i -> i).sum() + " Máximos mineros plata: " + maxSilverMiners.stream().mapToInt(i -> i).sum() + " Máximos mineros cobre: " + maxCopperMiners.stream().mapToInt(i -> i).sum() + " Máximos mineros comodines: " + maxJokersMiners + " Etapa: " + stage);
        boolean ok = false;
        if (stage == getMines().size()) {
            //int maxGain = 0;
            System.out.println("possibleGain " + possibleMaxGain.size());
//            for (Profit profit : possibleMaxGain) {
//                System.out.println(profit.toString());
//            }
            ok = true;
            return possibleMaxGain;

        } else {
            while (stage <= getMines().size() - 1 && !ok) {
                if (maxTotalMiners > 0) {
                    if (getMines().get(stage).getMineral().equalsIgnoreCase("oro")) {
                        int minersTemp = maxGoldMiners.stream().mapToInt(i -> i).sum();
//                        System.out.println(minersTemp);
//                        System.out.println("Etapa: " + stage);
                        //getMines().get(stage).showProfits();
                        if (getMines().get(stage).getMinersCapacity() <= minersTemp) {
                            //getMines().get(stage).showProfits();
                            for (Integer maxGoldMiner : maxGoldMiners) {
                                //System.out.println("Profit: " + profit.totalMiners());
                                int maxGold = maxGoldMiner;
                                for (Profit profit : getMines().get(stage).getProfits()) {
                                    //System.out.println("Max Gold: " + maxGoldMiner);
                                    if (!profit.isChecked()) {
                                        if (profit.totalMiners() == maxGold) {
                                            maxGold = maxGold - profit.getSpecialists();
                                            if (profit.getJokers() == maxGold) {
                                                //System.out.println(profit.toString());
                                                profit.setChecked(true);
                                                possibleMaxGain.add(profit);
                                                stage++;
                                                maxGoldMiners.remove(maxGoldMiner);
                                                maxJokersMiners = maxJokersMiners - profit.getJokers();
                                                maxTotalMiners = maxTotalMiners - profit.totalMiners();
                                                return possibleMaxGain = findMaximumGainOption(maxTotalMiners, maxGoldMiners, maxSilverMiners, maxCopperMiners, maxJokersMiners, possibleMaxGain, stage);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else if (getMines().get(stage).getMineral().equalsIgnoreCase("plata")) {
                        int minersTemp = maxSilverMiners.stream().mapToInt(i -> i).sum();
//                        System.out.println(minersTemp);
//                        System.out.println("Etapa: " + stage);
//                        getMines().get(stage).showProfits();
                        if (getMines().get(stage).getMinersCapacity() <= minersTemp) {
                            getMines().get(stage).showProfits();
                            for (Integer maxSilverMiner : maxSilverMiners) {
                                //System.out.println("Profit: " + profit.totalMiners());
                                int maxSilver = maxSilverMiner;
                                for (Profit profit : getMines().get(stage).getProfits()) {
                                    //System.out.println("Max Silver: " + maxSilverMiner);
                                    if (!profit.isChecked()) {
                                        if (profit.totalMiners() == maxSilver) {
                                            maxSilver = maxSilver - profit.getSpecialists();
                                            if (profit.getJokers() == maxSilver) {
                                                profit.setChecked(true);
                                                possibleMaxGain.add(profit);
                                                stage++;
                                                maxSilverMiners.remove(maxSilverMiner);
                                                maxJokersMiners = maxJokersMiners - profit.getJokers();
                                                maxTotalMiners = maxTotalMiners - profit.totalMiners();
                                                return possibleMaxGain = findMaximumGainOption(maxTotalMiners, maxGoldMiners, maxSilverMiners, maxCopperMiners, maxJokersMiners, possibleMaxGain, stage);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else if (getMines().get(stage).getMineral().equalsIgnoreCase("cobre")) {
                        int minersTemp = maxCopperMiners.stream().mapToInt(i -> i).sum();
//                        System.out.println(minersTemp);
//                        System.out.println("Etapa: " + stage);
//                        getMines().get(stage).showProfits();
                        if (getMines().get(stage).getMinersCapacity() <= minersTemp) {
                            getMines().get(stage).showProfits();
                            for (Integer maxCopperMiner : maxCopperMiners) {
                                //System.out.println("Profit: " + profit.totalMiners());
                                int maxCopper = maxCopperMiner;
                                for (Profit profit : getMines().get(stage).getProfits()) {
                                    //System.out.println("Max copper: " + maxCopperMiner);
                                    if (!profit.isChecked()) {
                                        if (profit.totalMiners() == maxCopper) {
                                            maxCopper = maxCopper - profit.getSpecialists();
                                            if (profit.getJokers() == maxCopper) {
                                                profit.setChecked(true);
                                                possibleMaxGain.add(profit);
                                                stage++;
                                                maxCopperMiners.remove(maxCopperMiner);
                                                maxJokersMiners = maxJokersMiners - profit.getJokers();
                                                maxTotalMiners = maxTotalMiners - profit.totalMiners();
                                                return possibleMaxGain = findMaximumGainOption(maxTotalMiners, maxGoldMiners, maxSilverMiners, maxCopperMiners, maxJokersMiners, possibleMaxGain, stage);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }//else para los otros minerales
                }
            }
        }
        return possibleMaxGain;
    }

    @Override
    public String toString() {
        String cadena;
        cadena = "Minerals S.A: " + getTotalMiners() + " " + getGoldMiners() + " " + getSilverMiners() + " " + getCopperMiners() + " Tiene " + getMines().size() + " minas" + "\t ganancias por mina: ";
        for (Mine mine : getMines()) {
            cadena = cadena + "\n" + getMines().indexOf(mine) + " " + mine.showProfits();
        }
        return cadena;
    }

    /**
     * @return the maxGains
     */
    public LinkedList<LinkedList<Profit>> getMaxGains() {
        return maxGains;
    }

    /**
     * @param maxGains the maxGains to set
     */
    public void setMaxGains(LinkedList<LinkedList<Profit>> maxGains) {
        this.maxGains = maxGains;
    }

}
