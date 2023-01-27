#ifndef EMPLOYEECLASS_H_
#define EMPLOYEECLASS_H_

#include <iostream>
#include <iomanip>
#include <string>

using namespace std;

class Employee {
	string name;
	int age;
	char gender;
	long ID;
	string phoneNumber;
	string jobTitle;
	double salary;
	int HireYear;
public:
	/* Default constructor */
	Employee();
	/* Constructor w/ parameters */
    Employee(string nme, long id, string jobTitle, double slry,int yr);
	/* Accessor Functions */
	string getName() const;
	long getID() const;
	string getJobTitle() const;
	double getSalary() const;
	int getHireYear() const;
    virtual void print();
};

#endif /* EMPLOYEECLASS_H_ */
