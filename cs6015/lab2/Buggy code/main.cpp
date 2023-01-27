#include "Department.h"
#include "employeeClass.h"
#include "programmerClass.h"
#include "softwarearchitectClass.h"
#include <vector>
#include <iostream>
#include <iomanip>
#include <string>

using namespace std;

//function used to print table header before the vector printing
void printHeader();

int main() 
{
	Department RnD("Research and Development", 1);
	Department IT("Info And Tech", 2);
	//creating new Employees
	Employee E1("Josh", 1, "Technology Specialist", 3000, 2015);
	Employee E2("Mary", 2, "Project Manager", 5500, 2010);
	Employee E3("Marc", 3, "Research Team Leader", 8000, 2007);
	//adding the employees to the appropriate vector
	RnD.Employees->push_back(E1);
	RnD.Employees->push_back(E2);
	RnD.Employees->push_back(E3);

	Programmer P1("Mike", 11, "Multimedia Prog.", 2500, 2014, E2.getName(), 5, "CSS");
	Programmer P2("Julie", 12, "Mainframe Sys.Prog.", 2150, 2013, E2.getName(), 5, "C++");
	Programmer P3("Albert", 13, "OS Prog.", 2300, 2015, E2.getName(), 9, "C++");
 
	RnD.Programmers->push_back(P1);
	RnD.Programmers->push_back(P2);
	RnD.Programmers->push_back(P3);

	SoftwareArchitect SA1("John",21, "Senior Software Eng.", 4899, 2011, E3.getName(), 6, 7);
	SoftwareArchitect SA2("Connor",24, "Software Developer", 1900, 2017, SA1.getName(), 8, 0);

	RnD.SoftwareArchitects->push_back(SA1);
	RnD.SoftwareArchitects->push_back(SA2);

	Programmer P4("Roy", 32, "Network Prog.", 1900 , 2016,"N/A", 7, "JAVA");
	Programmer P5("Jane", 35, "Tester", 1500, 2018, P4.getName(), 7, "JAVA");

	
	IT.Programmers->push_back(P4);
	IT.Programmers->push_back(P5);

	cout << "  Here is the List of all the Staff in the R&D Department: \n";
	cout << "  ------------------------------------------------------------\n";
	printHeader();
	//a Employee Vector that holds all types of staff
    vector<Employee*> *AllRnDEmployees1 = RnD.getAllStaff();

    for (int i = 0; i < AllRnDEmployees1->size(); i++)
	{
		cout << "\n";
		//using Base Class Pointers to Call the print function if each Type
        AllRnDEmployees1->at(i)->print();
	}

	cout << "  Here is the List of all the Staff in the IT Department: \n";
	cout << "  ------------------------------------------------------------\n";
	printHeader();
	//a Employee Vector that holds all types of staff
    vector<Employee*> *AllITEmployees = IT.getAllStaff();
    for (unsigned i= 0; i < AllITEmployees->size(); i++)
	{
		cout << "\n";
		//using Base Class Pointers to Call the print function if each Type
        AllITEmployees->at(i)->print();
	}

	cout << "\n Removing Mike from the company: ";
	cout << (RnD.RemoveEmployee(P1) ? "Succeeded\n" : "Failed\n");
	cout << " -------------------------------\n";	
	
	printHeader();
    vector<Employee*> *AllRnDEmployees2 = RnD.getAllStaff();
    //AllRnDEmployees = RnD.getAllStaff();
    for (unsigned i=0; i < AllRnDEmployees2->size(); i++)
	{
		cout << "\n";
        AllRnDEmployees2->at(i)->print();
	}

	cout << "\n  Here is the List of all the Programmers who know C++ in the R&D Department: \n";
	cout << "  ---------------------------------------------------------------------------\n";
	printHeader();
	vector<Programmer> *CppProgrammers = RnD.getProgrammersByLanguage("C++");
    for (unsigned i=0; i < CppProgrammers->size(); i++)
	{
		cout << "\n";
		CppProgrammers->at(i).print();
	}

	cout << "\n Listing All Project IDs: ";
	vector<int> *AllProjects = RnD.getAllProjectIDs();
    for (unsigned i=0; i < AllProjects->size(); i++)
	{
		cout <<" "<<AllProjects->at(i);
	}
	cout << "\n-------------------------\n";

	
	cout << "The Highest R&D Employee Salary is: " << RnD.CalculateMaxSalary(RnD.EMPLOYEE) << endl;
	cout << "The Highest R&D Programmer Salary is: " << RnD.CalculateMaxSalary(RnD.PROGRAMMER) << endl;
	cout << "The Highest R&D Software Architect Salary is: " << RnD.CalculateMaxSalary(RnD.SOFTWAREARCHITECTS) << endl;
	cout << "\n";
	cout << "The Average R&D Employee Salary is: " << RnD.CalculateAverageSalary(RnD.EMPLOYEE) << endl;
	cout << "The Average R&D Programmer Salary is: " << RnD.CalculateAverageSalary(RnD.PROGRAMMER) << endl;
	cout << "The Average R&D Software Architect Salary is: " << RnD.CalculateAverageSalary(RnD.SOFTWAREARCHITECTS) << endl;
	cout << "\n";
	cout << "The Average IT Employee Salary is: " << IT.CalculateAverageSalary(IT.EMPLOYEE) << endl;
	cout << "The Average IT Programmer Salary is: " << IT.CalculateAverageSalary(IT.PROGRAMMER) << endl;
	cout << "The Average IT Software Architect Salary is: " << IT.CalculateAverageSalary(IT.SOFTWAREARCHITECTS) << endl;

	return 0;
}

void printHeader()
{
	cout << setw(7) << "Name"
		<< setw(4) << "ID"
		<< setw(15) << "Title"
		<< setw(18) << setprecision(2) << fixed << "Salary"
		<< setw(13) << "Year Hired"
		<< setw(12) << "Supervisor"
		<< setw(11) << "ProjectID"
		<< setw(11) << "Prog.Lang."
		<< setw(11) << "#Exp Years"
		<< "\n";

}
