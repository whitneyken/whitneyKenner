#include "programmerClass.h"

Programmer::Programmer() {
	supervisorName = "";
	ProjectID = 0;
	Language = "";

}

Programmer::Programmer(string nme, long id, string jobTtle, double slry, int yr, string superName, int projctid, string PrgLang)

					   : Employee(nme, id, jobTtle, slry, yr) {

	supervisorName = superName;

	(projctid > 0) ? ProjectID = projctid : ProjectID = 0;
	
	Language = PrgLang;
}

/* Accessor Functions */


string Programmer::getSupervisorName() const {
	return supervisorName;
}

int Programmer::getProjectID() const {
	return ProjectID;
}

string Programmer::getProgLanguage() const
{
	return Language;
}

void Programmer::print()
{
	cout << setw(7) << getName()
		<< setw(4) << getID()
		<< setw(24) << getJobTitle()
		<< setw(10) << setprecision(2) << fixed << getSalary()
		<< setw(10) << getHireYear()
		<< setw(10) << getSupervisorName()
		<< setw(10) << getProjectID()
		<< setw(12) << getProgLanguage()
		<< "\n";
}



