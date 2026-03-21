package iuh.fit.se;

interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers();
}
