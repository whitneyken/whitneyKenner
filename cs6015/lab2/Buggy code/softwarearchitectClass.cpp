#include "softwarearchitectClass.h"

SoftwareArchitect::SoftwareArchitect() {
	supervisorName = "";
	ProjectID = 0;
	experienceYears = 0;
}

SoftwareArchitect::SoftwareArchitect(string nme, long id, string jobTtle, double slry, int yr,
						string superName, int projectid, int exp)
									: Employee(nme, id, jobTtle, slry, yr){

	
	supervisorName = superName;

	(projectid > 0) ? ProjectID = projectid : ProjectID = 0;

	(exp > 0) ? experienceYears = exp : experienceYears = 0;
}


/* Accessor Functions */

string SoftwareArchitect::getSupervisorName() const {
	return supervisorName;
}

int SoftwareArchitect::getProjectID() const
{
	return ProjectID;
}



int SoftwareArchitect::getExperienceYears() const {
	return experienceYears;
}

void SoftwareArchitect::print()
{
	cout << setw(7) << getName()
		<< setw(4) << getID()
		<< setw(24) << getJobTitle()
		<< setw(10) << setprecision(2) << fixed << getSalary()
		<< setw(10) << getHireYear()
		<< setw(10) << getSupervisorName()
		<< setw(10) << getProjectID()
		<< setw(21) << getExperienceYears()
		<< "\n";
}

