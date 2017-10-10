#include <iostream>
using namespace std;

#define NAME_SIZE 50 // Defines a macro

class Person {
  int id; // all members are private by default
  char name[NAME_SIZE];

  public:
    void aboutMe() {
      cout << "I am a person.";
    }

    // If just need to initialize primitive types
    Person(int a) {
      id = a;
    }

    // Data member id is assigned before the actual object is created
    // necesarry when the fields are constant or class types
    Person(int a) : id(a) {
      // some code
    }

    ~Person() {
      delete obj; //free any memory allocated within class
    }
};

// public can be removed to use private mode.
class Student : public Person {
  public:
    void aboutMe() {
      cout << "I am a student.";
    }
};

int main () {
  Student * p = new Student();
  p->aboutMe(); // prints "I am a student."
  delete p; // IMPORTANT! Make sure to delete allocated memory

  return 0;
}
