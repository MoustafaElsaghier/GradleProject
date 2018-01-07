package com.elsahier;
import java.util.ArrayList;
	
public class Jokes {

    public String getJoke() {
        ArrayList<String> jokes = new ArrayList<>();
        jokes.add("What is the difference between a snowman and a snow woman?\nSnowballs");
        jokes.add("My dog used to chase people on a bike a lot It got so bad finally I had to take his bike away");
        jokes.add("Anton, do you think I’m a bad mother?\nMy name is Paul");
        jokes.add("Can a kangaroo jump higher than a house? \nOf course a house doesn’t jump at all");
        int randomIndex = (int) (Math.random() * jokes.size()) - 1;
        return jokes.get(randomIndex);
    }

    public static void main(String[] args) {
        System.out.println("ok");
    }
}
