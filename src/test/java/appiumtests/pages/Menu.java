package appiumtests.pages;

public class Menu extends Common{
    public Menu play(){
        try {
            Thread.sleep(20000); // Pausa durante 1000 milisegundos (1 segundo)
        } catch (InterruptedException e) {
            // Manejo de excepciones aquí
        }
        clickElement(2006, 965);
        return this;
    }

    public Menu wihGameMode(String gameMode){
        try {
            Thread.sleep(5000); // Pausa durante 1000 milisegundos (1 segundo)
        } catch (InterruptedException e) {
            // Manejo de excepciones aquí
        }
        switch (gameMode.toUpperCase()) {
            case ("FIESTA DE PENGU"):
                rightSwipe();
                clickElement(332, 688);
                break;
            case ("NORMAL"):
                rightSwipe();
                clickElement(746, 688);
                break;
            case ("CLASIFICATORIA"):
                rightSwipe();
                clickElement(1169, 688);
                break;
            case ("DUO DINAMICO"):
                rightSwipe();
                clickElement(1597, 688);
                break;
            case ("HYPER ROLL"):
                rightSwipe();
                clickElement(2026, 688);
                break;
            case ("TUTORIAL"):
                leftSwipe();
                clickElement(2194, 688);
                acceptTutorial();
                break;
        }
        return this;
    }

    public Menu startGame(){
        try {
            Thread.sleep(5000); // Pausa durante 1000 milisegundos (1 segundo)
        } catch (InterruptedException e) {
            // Manejo de excepciones aquí
        }
        clickElement(2021, 967);
        return this;
    }

    public Menu acceptGame(){
        try {
            Thread.sleep(5000); // Pausa durante 1000 milisegundos (1 segundo)
        } catch (InterruptedException e) {
            // Manejo de excepciones aquí
        }
        clickElement(1198, 818);
        return this;
    }

    private void acceptTutorial(){
        clickElement(1349, 672);
        try {
            Thread.sleep(30000); // Pausa durante 1000 milisegundos (1 segundo)
        } catch (InterruptedException e) {
            // Manejo de excepciones aquí
        }
    }
}
