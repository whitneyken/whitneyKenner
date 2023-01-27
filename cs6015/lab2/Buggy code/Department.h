#ifndef DEPARTMENT_H
#define DEPARTMENT_H

#include "employeeClass.h"
#include "programmerClass.h"
#include "softwarearchitectClass.h"
#include <vector>

// This class is used to store all Staff Info and Operate on them
class Department
{
private:
	string Name;
	int ID;
	//private function that checks if an ID already exists
	bool checkID(int ID, vector<int>* v);
public:
	// Defines three possible Types Of Staff
	enum StaffTypes	{ EMPLOYEE, PROGRAMMER, SOFTWAREARCHITECTS };

	//Public Staff Vectors
	vector<Employee>* Employees;
	vector<Programmer>* Programmers;
	vector<SoftwareArchitect>* SoftwareArchitects;

	//empty Constructor
	Department();
	//Constructor For Department from its name, ID
	Department(string N, int id);

	//returns the highest Salary for a given Staff Type
	double CalculateMaxSalary(StaffTypes);
	//returns the Average Salary for a given Staff Type
	int CalculateAverageSalary(StaffTypes);
	//return a vector containing all the Staff (casted to base class Type)
    vector<Employee*> *getAllStaff();
	//return a Programmer vector that knows a certain programming language
	vector<Programmer> *getProgrammersByLanguage(string Lang);
	//deletes an employee from any of the staff vectors
	bool RemoveEmployee(Employee E);
	//returns a vector containing all the Diff Project IDs
	vector<int> *getAllProjectIDs();
	//Destructor that deletes the pointers after exiting
	~Department();
};

#endif // !DEPARTMENT_H

