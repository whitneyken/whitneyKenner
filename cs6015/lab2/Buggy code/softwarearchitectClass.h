#ifndef SOFTWAREARCHITECTCLASS_H_
#define SOFTWAREARCHITECTCLASS_H_

#include "employeeClass.h"
#include <string>
using namespace std;

class SoftwareArchitect : public Employee {
	string supervisorName;
	int ProjectID;
	int experienceYears;
public:
	/* Default constructor */
	SoftwareArchitect();
	/* Constructor w/ parameters */
	SoftwareArchitect(string nme, long id, string jobTtle, double slry, int yr,
		string superName, int projectid, int exp);
	/* Accessor Functions */
	string getSupervisorName() const;
	int getProjectID() const;
	int getExperienceYears() const;
	void print();
};

#endif /* SOFTWAREARCHITECTCLASS_H_ */
