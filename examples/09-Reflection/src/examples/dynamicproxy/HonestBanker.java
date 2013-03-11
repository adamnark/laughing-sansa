package examples.dynamicproxy;

/**
 *
 * @author blecherl
 */
public class HonestBanker implements Banker {

    private int money;

    @Override
    public int getMoney() {
        return money;
    }

    @Override
    public void setMoney(int money) {
        this.money = money;
    }
}
