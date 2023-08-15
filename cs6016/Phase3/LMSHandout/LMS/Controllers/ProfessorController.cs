using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;
using System.Text.Json;
using System.Threading.Tasks;
using LMS.Models.LMSModels;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using NuGet.ContentModel;

// For more information on enabling MVC for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace LMS_CustomIdentity.Controllers
{
    [Authorize(Roles = "Professor")]
    public class ProfessorController : Controller
    {

        private readonly LMSContext db;

        public ProfessorController(LMSContext _db)
        {
            db = _db;
        }

        public IActionResult Index()
        {
            return View();
        }

        public IActionResult Students(string subject, string num, string season, string year)
        {
            ViewData["subject"] = subject;
            ViewData["num"] = num;
            ViewData["season"] = season;
            ViewData["year"] = year;
            return View();
        }

        public IActionResult Class(string subject, string num, string season, string year)
        {
            ViewData["subject"] = subject;
            ViewData["num"] = num;
            ViewData["season"] = season;
            ViewData["year"] = year;
            return View();
        }

        public IActionResult Categories(string subject, string num, string season, string year)
        {
            ViewData["subject"] = subject;
            ViewData["num"] = num;
            ViewData["season"] = season;
            ViewData["year"] = year;
            return View();
        }

        public IActionResult CatAssignments(string subject, string num, string season, string year, string cat)
        {
            ViewData["subject"] = subject;
            ViewData["num"] = num;
            ViewData["season"] = season;
            ViewData["year"] = year;
            ViewData["cat"] = cat;
            return View();
        }

        public IActionResult Assignment(string subject, string num, string season, string year, string cat, string aname)
        {
            ViewData["subject"] = subject;
            ViewData["num"] = num;
            ViewData["season"] = season;
            ViewData["year"] = year;
            ViewData["cat"] = cat;
            ViewData["aname"] = aname;
            return View();
        }

        public IActionResult Submissions(string subject, string num, string season, string year, string cat, string aname)
        {
            ViewData["subject"] = subject;
            ViewData["num"] = num;
            ViewData["season"] = season;
            ViewData["year"] = year;
            ViewData["cat"] = cat;
            ViewData["aname"] = aname;
            return View();
        }

        public IActionResult Grade(string subject, string num, string season, string year, string cat, string aname, string uid)
        {
            ViewData["subject"] = subject;
            ViewData["num"] = num;
            ViewData["season"] = season;
            ViewData["year"] = year;
            ViewData["cat"] = cat;
            ViewData["aname"] = aname;
            ViewData["uid"] = uid;
            return View();
        }

        /*******Begin code to modify********/


        /// <summary>
        /// Returns a JSON array of all the students in a class.
        /// Each object in the array should have the following fields:
        /// "fname" - first name
        /// "lname" - last name
        /// "uid" - user ID
        /// "dob" - date of birth
        /// "grade" - the student's grade in this class
        /// </summary>
        /// <param name="subject">The course subject abbreviation</param>
        /// <param name="num">The course number</param>
        /// <param name="season">The season part of the semester for the class the assignment belongs to</param>
        /// <param name="year">The year part of the semester for the class the assignment belongs to</param>
        /// <returns>The JSON array</returns>
        public IActionResult GetStudentsInClass(string subject, int num, string season, int year)
        {
            var query = from st in db.Enrolleds
                        where st.ClassNavigation.Season == season &&
                        st.ClassNavigation.Year == year &&
                        st.ClassNavigation.ListingNavigation.Number == num &&
                        st.ClassNavigation.ListingNavigation.Department == subject
                        select new
                        {
                            fname = st.StudentNavigation.FName,
                            lname = st.StudentNavigation.LName,
                            uid = st.StudentNavigation.UId,
                            dob = st.StudentNavigation.Dob,
                            grade = (st.Grade == null ? null : st.Grade),
                        };

            return Json(query);
        }



        /// <summary>
        /// Returns a JSON array with all the assignments in an assignment category for a class.
        /// If the "category" parameter is null, return all assignments in the class.
        /// Each object in the array should have the following fields:
        /// "aname" - The assignment name
        /// "cname" - The assignment category name.
        /// "due" - The due DateTime
        /// "submissions" - The number of submissions to the assignment
        /// </summary>
        /// <param name="subject">The course subject abbreviation</param>
        /// <param name="num">The course number</param>
        /// <param name="season">The season part of the semester for the class the assignment belongs to</param>
        /// <param name="year">The year part of the semester for the class the assignment belongs to</param>
        /// <param name="category">The name of the assignment category in the class, 
        /// or null to return assignments from all categories</param>
        /// <returns>The JSON array</returns>
        public IActionResult GetAssignmentsInCategory(string subject, int num, string season, int year, string category)
        {

            //If the "category" parameter is null, return all assignments in the class.

            if (category == null)
            {
                var query = from ass in db.Assignments
                            where
                            ass.CategoryNavigation.InClassNavigation.Season == season &&
                            ass.CategoryNavigation.InClassNavigation.Year == year &&
                            ass.CategoryNavigation.InClassNavigation.ListingNavigation.Department == subject &&
                            ass.CategoryNavigation.InClassNavigation.ListingNavigation.Number == num

                            select new
                            {
                                aname = ass.Name,
                                cname = ass.CategoryNavigation.Name,
                                due = ass.Due,
                                submissions = (from d in db.Submissions where d.Assignment == ass.AssignmentId select d.Student).Count()
                            };
                return Json(query);

            }
            else
            {


                var query = from ass in db.Assignments
                            where ass.CategoryNavigation.Name == category &&
                            ass.CategoryNavigation.InClassNavigation.Season == season &&
                            ass.CategoryNavigation.InClassNavigation.Year == year &&
                            ass.CategoryNavigation.InClassNavigation.ListingNavigation.Department == subject &&
                            ass.CategoryNavigation.InClassNavigation.ListingNavigation.Number == num

                            select new
                            {
                                aname = ass.Name,
                                cname = ass.CategoryNavigation.Name,
                                due = ass.Due,
                                submissions = (from d in db.Submissions where d.Assignment == ass.AssignmentId select d.Student).Count()
                            };
                return Json(query);
            }

        }


        /// <summary>
        /// Returns a JSON array of the assignment categories for a certain class.
        /// Each object in the array should have the folling fields:
        /// "name" - The category name
        /// "weight" - The category weight
        /// </summary>
        /// <param name="subject">The course subject abbreviation</param>
        /// <param name="num">The course number</param>
        /// <param name="season">The season part of the semester for the class the assignment belongs to</param>
        /// <param name="year">The year part of the semester for the class the assignment belongs to</param>
        /// <param name="category">The name of the assignment category in the class</param>
        /// <returns>The JSON array</returns>
        public IActionResult GetAssignmentCategories(string subject, int num, string season, int year)
        {
            var query = from a in db.AssignmentCategories
                        where a.InClassNavigation.Year == year &&
                        a.InClassNavigation.Season == season &&
                        a.InClassNavigation.ListingNavigation.Number == num &&
                        a.InClassNavigation.ListingNavigation.Department == subject
                        select new
                        {
                            name = a.Name,
                            weight = a.Weight
                        };

            return Json(query);
        }

        /// <summary>
        /// Creates a new assignment category for the specified class.
        /// If a category of the given class with the given name already exists, return success = false.
        /// </summary>
        /// <param name="subject">The course subject abbreviation</param>
        /// <param name="num">The course number</param>
        /// <param name="season">The season part of the semester for the class the assignment belongs to</param>
        /// <param name="year">The year part of the semester for the class the assignment belongs to</param>
        /// <param name="category">The new category name</param>
        /// <param name="catweight">The new category weight</param>
        /// <returns>A JSON object containing {success = true/false} </returns>
        public IActionResult CreateAssignmentCategory(string subject, int num, string season, int year, string category, int catweight)
        {
            var query = from c in db.AssignmentCategories
                        where c.Name == category &&
                        c.InClassNavigation.Year == year &&
                        c.InClassNavigation.Season == season &&
                        c.InClassNavigation.ListingNavigation.Number == num &&
                        c.InClassNavigation.ListingNavigation.Department == subject
                        select c;
            if (query.Count() > 0)
            {
                return Json(new { success = false });
            }
            else
            {
                AssignmentCategory assCat = new AssignmentCategory();
                assCat.Name = category;
                assCat.Weight = (uint)catweight;
                assCat.InClass = (from c in db.Classes
                                  where
                                  c.Year == year &&
                                  c.Season == season &&
                                  c.ListingNavigation.Number == num &&
                                  c.ListingNavigation.Department == subject
                                  select c.ClassId).FirstOrDefault();

                db.AssignmentCategories.Add(assCat);

                db.SaveChanges();

                return Json(new { success = true });
            }

        }

        /// <summary>
        /// Creates a new assignment for the given class and category.
        /// </summary>
        /// <param name="subject">The course subject abbreviation</param>
        /// <param name="num">The course number</param>
        /// <param name="season">The season part of the semester for the class the assignment belongs to</param>
        /// <param name="year">The year part of the semester for the class the assignment belongs to</param>
        /// <param name="category">The name of the assignment category in the class</param>
        /// <param name="asgname">The new assignment name</param>
        /// <param name="asgpoints">The max point value for the new assignment</param>
        /// <param name="asgdue">The due DateTime for the new assignment</param>
        /// <param name="asgcontents">The contents of the new assignment</param>
        /// <returns>A JSON object containing success = true/false</returns>
        public IActionResult CreateAssignment(string subject, int num, string season, int year, string category, string asgname, int asgpoints, DateTime asgdue, string asgcontents)
        {
            var query = from a in db.Assignments
                        where a.Name == asgname &&
                        a.CategoryNavigation.Name == category &&
                        a.CategoryNavigation.InClassNavigation.Year == year &&
                        a.CategoryNavigation.InClassNavigation.Season == season &&
                        a.CategoryNavigation.InClassNavigation.ListingNavigation.Department == subject &&
                        a.CategoryNavigation.InClassNavigation.ListingNavigation.Number == num
                        select a;

            if (query.Count() > 0)
            {
                return Json(new { success = false });
            }
            else
            {

                Assignment ass = new Assignment();
                ass.Name = asgname;
                ass.Contents = asgcontents;
                ass.Due = asgdue;
                ass.MaxPoints = (uint)asgpoints;
                ass.Category = (from c in db.AssignmentCategories
                                where c.Name == category &&
                                c.InClassNavigation.Year == year &&
                                c.InClassNavigation.Season == season &&
                                c.InClassNavigation.ListingNavigation.Number == num &&
                                c.InClassNavigation.ListingNavigation.Department == subject
                                select c.CategoryId).FirstOrDefault();


                db.Assignments.Add(ass);
                db.SaveChanges();
                var allStudentsInClass = (from e in db.Enrolleds
                                         where
                                         e.ClassNavigation.Season == season &&
                                         e.ClassNavigation.Year == year &&
                                         e.ClassNavigation.ListingNavigation.Department == subject &&
                                         e.ClassNavigation.ListingNavigation.Number == num
                                         select e.Student).ToList();
                foreach (var student in allStudentsInClass)
                {
                    string updatedGrade = LetterGradeCalculator(subject, num, season, year, student);

                    var toUpdate = from e in db.Enrolleds
                                   where e.Student == student &&
                                   e.ClassNavigation.Season == season &&
                                   e.ClassNavigation.Year == year &&
                                   e.ClassNavigation.ListingNavigation.Department == subject &&
                                   e.ClassNavigation.ListingNavigation.Number == num
                                   select e;

                    Enrolled en = toUpdate.FirstOrDefault();
                    en.Grade = updatedGrade;
                    db.SaveChanges();
                }

                
                return Json(new { success = true });
            }

        }


        /// <summary>
        /// Gets a JSON array of all the submissions to a certain assignment.
        /// Each object in the array should have the following fields:
        /// "fname" - first name
        /// "lname" - last name
        /// "uid" - user ID
        /// "time" - DateTime of the submission
        /// "score" - The score given to the submission
        /// 
        /// </summary>
        /// <param name="subject">The course subject abbreviation</param>
        /// <param name="num">The course number</param>
        /// <param name="season">The season part of the semester for the class the assignment belongs to</param>
        /// <param name="year">The year part of the semester for the class the assignment belongs to</param>
        /// <param name="category">The name of the assignment category in the class</param>
        /// <param name="asgname">The name of the assignment</param>
        /// <returns>The JSON array</returns>
        public IActionResult GetSubmissionsToAssignment(string subject, int num, string season, int year, string category, string asgname)
        {
            var query = from s in db.Submissions
                        where s.AssignmentNavigation.Name == asgname &&
                        s.AssignmentNavigation.CategoryNavigation.Name == category &&
                        s.AssignmentNavigation.CategoryNavigation.InClassNavigation.Season == season &&
                        s.AssignmentNavigation.CategoryNavigation.InClassNavigation.Year == year &&
                        s.AssignmentNavigation.CategoryNavigation.InClassNavigation.ListingNavigation.Number == num &&
                        s.AssignmentNavigation.CategoryNavigation.InClassNavigation.ListingNavigation.Department == subject
                        select new
                        {
                            fname = (from d in db.Students where
                                     d.UId == s.Student select d.FName).FirstOrDefault(),
                            lname = (from d in db.Students
                                     where
                                     d.UId == s.Student
                                     select d.LName).FirstOrDefault(),
                            uid = s.Student,
                            time = s.Time,
                            score = s.Score

                        };
            return Json(query);
        }


        /// <summary>
        /// Set the score of an assignment submission
        /// </summary>
        /// <param name="subject">The course subject abbreviation</param>
        /// <param name="num">The course number</param>
        /// <param name="season">The season part of the semester for the class the assignment belongs to</param>
        /// <param name="year">The year part of the semester for the class the assignment belongs to</param>
        /// <param name="category">The name of the assignment category in the class</param>
        /// <param name="asgname">The name of the assignment</param>
        /// <param name="uid">The uid of the student who's submission is being graded</param>
        /// <param name="score">The new score for the submission</param>
        /// <returns>A JSON object containing success = true/false</returns>
        public IActionResult GradeSubmission(string subject, int num, string season, int year, string category, string asgname, string uid, int score)
        {
            var query = from s in db.Submissions
                        where
                        s.Student == uid &&
                        s.AssignmentNavigation.Name == asgname &&
                        s.AssignmentNavigation.CategoryNavigation.Name == category &&
                        s.AssignmentNavigation.CategoryNavigation.InClassNavigation.Year == year &&
                        s.AssignmentNavigation.CategoryNavigation.InClassNavigation.Season == season &&
                        s.AssignmentNavigation.CategoryNavigation.InClassNavigation.ListingNavigation.Department == subject &&
                        s.AssignmentNavigation.CategoryNavigation.InClassNavigation.ListingNavigation.Number == num
                        select s;
            if (query.Count() > 0)
            {
                Submission sub = query.FirstOrDefault();
                sub.Score = (uint)score;
                db.SaveChanges();
                string updatedGrade = LetterGradeCalculator(subject, num, season, year, uid);

                var toUpdate = from e in db.Enrolleds
                               where e.Student == uid &&
                               e.ClassNavigation.Season == season &&
                               e.ClassNavigation.Year == year &&
                               e.ClassNavigation.ListingNavigation.Department == subject &&
                               e.ClassNavigation.ListingNavigation.Number == num
                               select e;

                Enrolled en = toUpdate.FirstOrDefault();
                en.Grade = updatedGrade;
                db.SaveChanges();

                return Json(new { success = true });


            }
            return Json(new { success = false });
        }


        /// <summary>
        /// Returns a JSON array of the classes taught by the specified professor
        /// Each object in the array should have the following fields:
        /// "subject" - The subject abbreviation of the class (such as "CS")
        /// "number" - The course number (such as 5530)
        /// "name" - The course name
        /// "season" - The season part of the semester in which the class is taught
        /// "year" - The year part of the semester in which the class is taught
        /// </summary>
        /// <param name="uid">The professor's uid</param>
        /// <returns>The JSON array</returns>
        public IActionResult GetMyClasses(string uid)
        {
            var query = from c in db.Classes
                        where
                        c.TaughtBy == uid
                        select new
                        {
                            subject = c.ListingNavigation.Department,
                            number = c.ListingNavigation.Number,
                            name = c.ListingNavigation.Name,
                            season = c.Season,
                            year = c.Year
                        };
            return Json(query);
        }



        /*******End code to modify********/


        public string LetterGradeCalculator(string subject, int num, string season, int year, string uid)
        {

            //var allAssignmentsQuery = from a in db.Assignments
            //                          where
            //                          a.CategoryNavigation.InClassNavigation.Season == season //&&
            //                                                                                  //a.CategoryNavigation.InClassNavigation.Year == year &&
            //                                                                                  //a.CategoryNavigation.InClassNavigation.ListingNavigation.Department == subject &&
            //                                                                                  //a.CategoryNavigation.InClassNavigation.ListingNavigation.Number == num
            //                          select a;
            //{
            //    maxPoints = a.MaxPoints,
            //    score = 100,
            //    //a.Submissions.Where(id => id.Student == uid),
            //    weight = a.CategoryNavigation.Weight
            //};

            var allAssignmentsQuery =
                (from e in db.Enrolleds
                 where e.Student == uid &&
                 e.ClassNavigation.Season == season &&
                 e.ClassNavigation.Year == year &&
                 e.ClassNavigation.ListingNavigation.Number == num &&
                 e.ClassNavigation.ListingNavigation.Department == subject
                 join assC in db.AssignmentCategories
                 on e.Class equals assC.InClass
                 into enAssCat
                 from en in enAssCat.DefaultIfEmpty()
                 join ass in db.Assignments
                 on en.CategoryId equals ass.Category
                 into assign
                 from a in assign
                 select new
                 {
                     score =
                     (from s in db.Submissions
                      where s.Student == uid &&
                      s.Assignment == a.AssignmentId
                      select s.Score) == null ? 0 : (from s in db.Submissions
                                                     where s.Student == uid &&
                                                     s.Assignment == a.AssignmentId
                                                     select s.Score).FirstOrDefault(),
                     maxPoints = a.MaxPoints,
                     weight = en.Weight
                 }).ToList();
            Console.WriteLine("*************************************************");
            Console.WriteLine("there are this many scores: {0}", allAssignmentsQuery.Count().ToString());
            Console.WriteLine("************************************");
            float totalmaxscore = 0;
            float totalStudentScore = 0;
            foreach (var points in allAssignmentsQuery)
            {
                //totalmaxscore = 100;
                //totalStudentScore = 80;

                totalmaxscore += (float)points.maxPoints * ((float)points.weight / 100);
                Console.WriteLine("The max points is: {0}", points.maxPoints);
                totalStudentScore += (float)points.score * ((float)points.weight / 100);
                Console.WriteLine("The score is: {0}", points.score);
            }
            Console.WriteLine("*************************************************");
            Console.WriteLine("total max us is{0}", totalmaxscore.ToString());
            Console.WriteLine("the total student score is: {0} ", totalStudentScore.ToString());
            Console.WriteLine("************************************");
            var catWeightQuery =
                from e in db.Enrolleds
                where e.Student == uid &&
                e.ClassNavigation.Season == season &&
                e.ClassNavigation.Year == year &&
                e.ClassNavigation.ListingNavigation.Number == num &&
                e.ClassNavigation.ListingNavigation.Department == subject
                join assC in db.AssignmentCategories
                
                on e.Class equals assC.InClass
                
                into enAssCat
                from ass in enAssCat where
                ass.Assignments.Where(a => a.Category == ass.CategoryId).Any()
                select ass.Weight;

            uint totalCatWeight = 0;





            foreach (var val in catWeightQuery)
            {
                totalCatWeight += val;
            }
            Console.WriteLine("The total category weight is: {0}", totalCatWeight);
            float scaleFactor = 100 / (float)totalCatWeight;
            float scaledMaxScore = scaleFactor * totalmaxscore;
            float scaledStudentScore = scaleFactor * totalStudentScore;
            float finalScore = scaledStudentScore / scaledMaxScore;

            finalScore = finalScore * 100;
            Console.WriteLine("*************************************************");
            Console.WriteLine("final score is{0}", finalScore.ToString());
            Console.WriteLine("************************************");
            string grade = "F";
            if (finalScore > 93)
            {
                grade = "A";
            }
            else if (finalScore <= 93 && finalScore > 89)
            {
                grade = "A-";
            }
            else if (finalScore <= 89 && finalScore > 86)
            {
                grade = "B+";
            }
            else if (finalScore <= 86 && finalScore > 83)
            {
                grade = "B";
            }
            else if (finalScore <= 83 && finalScore > 79)
            {
                grade = "B-";
            }
            else if (finalScore <= 79 && finalScore > 76)
            {
                grade = "C+";
            }
            else if (finalScore <= 76 && finalScore > 73)
            {
                grade = "C";
            }
            else if (finalScore <= 73 && finalScore > 69)
            {
                grade = "C-";
            }
            else if (finalScore <= 69 && finalScore > 66)
            {
                grade = "D+";
            }
            else if (finalScore <= 66 && finalScore > 63)
            {
                grade = "D";
            }
            else if (finalScore <= 63 && finalScore > 59)
            {
                grade = "D-";
            }
            return grade;
        }
        /*******End code to modify********/
    }
}


