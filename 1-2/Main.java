import java.util.Calendar;
import java.util.Date;

// Hierarquia de Produtos
class Pizza {
    private String[] ingredients;

    public Pizza(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public void listIngredients() {
        System.out.println("Pizza Ingredients: " + String.join(", ", ingredients));
    }
}

class Calzone {
    private String[] ingredients;

    public Calzone(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public void listIngredients() {
        System.out.println("Calzone Ingredients: " + String.join(", ", ingredients));
    }
}

// Hierarquia de Fábricas
interface PizzaFactory {
    Pizza createPizza();
}

interface CalzoneFactory {
    Calzone createCalzone();
}

// Fábricas concretas
class CalabresaPizzaFactory implements PizzaFactory {
    @Override
    public Pizza createPizza() {
        return new Pizza(new String[]{"cheese", "calabresa", "tomato"});
    }
}

class PresuntoPizzaFactory implements PizzaFactory {
    @Override
    public Pizza createPizza() {
        return new Pizza(new String[]{"cheese", "presunto", "tomato"});
    }
}

class CalabresaCalzoneFactory implements CalzoneFactory {
    @Override
    public Calzone createCalzone() {
        return new Calzone(new String[]{"cheese", "calabresa", "tomato"});
    }
}

class PresuntoCalzoneFactory implements CalzoneFactory {
    @Override
    public Calzone createCalzone() {
        return new Calzone(new String[]{"cheese", "presunto", "tomato"});
    }
}

// Cliente
public class Main {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek == Calendar.SUNDAY) {
            System.out.println("A pizzaria está fechada no domingo.");
            return;
        }

        PizzaFactory pizzaFactory;
        CalzoneFactory calzoneFactory;

        if (dayOfWeek == Calendar.MONDAY || dayOfWeek == Calendar.WEDNESDAY || dayOfWeek == Calendar.FRIDAY) {
            pizzaFactory = new CalabresaPizzaFactory();
            calzoneFactory = new CalabresaCalzoneFactory();
        } else {
            pizzaFactory = new PresuntoPizzaFactory();
            calzoneFactory = new PresuntoCalzoneFactory();
        }

        Pizza pizza = pizzaFactory.createPizza();
        Calzone calzone = calzoneFactory.createCalzone();

        pizza.listIngredients();
        calzone.listIngredients();
    }
}