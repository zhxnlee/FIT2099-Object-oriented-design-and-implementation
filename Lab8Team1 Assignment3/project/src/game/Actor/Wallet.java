package game.Actor;
/**
 * Class that represents the Wallet of the Player
 */


public class Wallet {
    int money = 0;
    /**
     * Constructor.
     *
     */
    public Wallet() {
        this.money = money;
    }
    /**
     * To retrieve the Player's money
     *
     */
    public int getMoney() {
        return money;
    }
    /**
     * To set/change the Player's money
     *
     */
    public void setMoney(int money) {
        this.money = money;
    }
    /**
     * To add money to the Wallet
     *
     */
    public void addMoney(int coin){
        this.money += coin;
    }
    /**
     * To spend the money of the Wallet
     *
     */
    public void withdraw(int money){
        this.money -= money;
    }
}
