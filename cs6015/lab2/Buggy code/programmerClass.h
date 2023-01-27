#ifndef PROGRAMMERCLASS_H_
#define PROGRAMMERCLASS_H_
#include <string>
#include "employeeClass.h"
using namespace std;

class Programmer : public Employee {
	string supervisorName;
	int ProjectID;
	string Language;
public:
    /* Default constructor */
	Programmer();
	/* Constructor w/ parameters */
	Programmer(string nme, long id, string jobTtle, double slry, int yr,
		string superName, int projctid, string PrgLang);
	/* Accessor Functions */
	string getSupervisorName() const;
	int getProjectID() const;
	string getProgLanguage() const;
	void print();
};

#endif /* PROGRAMMERCLASS_H_ */
