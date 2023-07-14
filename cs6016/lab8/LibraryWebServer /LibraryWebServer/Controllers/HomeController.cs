using LibraryWebServer.Models;
using Microsoft.AspNetCore.Mvc;
using System.Diagnostics;
using System.Runtime.CompilerServices;

[assembly: InternalsVisibleTo( "TestProject1" )]
namespace LibraryWebServer.Controllers
{
    public class HomeController : Controller
    {

        // WARNING:
        // This very simple web server is designed to be as tiny and simple as possible
        // This is NOT the way to save user data.
        // This will only allow one user of the web server at a time (aside from major security concerns).
        private static string user = "";
        private static int card = -1;

        private readonly LibraryContext db;
        public HomeController(LibraryContext _db)
        {
            db = _db;
        }

        /// <summary>
        /// Given a Patron name and CardNum, verify that they exist and match in the database.
        /// If the login is successful, sets the global variables "user" and "card"
        /// </summary>
        /// <param name="name">The Patron's name</param>
        /// <param name="cardnum">The Patron's card number</param>
        /// <returns>A JSON object with a single field: "success" with a boolean value:
        /// true if the login is accepted, false otherwise.
        /// </returns>
        [HttpPost]
        public IActionResult CheckLogin( string name, int cardnum )
        {
            // TODO: Fill in. Determine if login is successful or not.
            bool loginSuccessful = false;
            var query = from N in db.Patrons where N.Name == name && N.CardNum == cardnum select new {userName = N.Name, userCard = N.CardNum};

            if(query.Count() == 1)
            {
                loginSuccessful = true;
            }

            if ( !loginSuccessful )
            {
                return Json( new { success = false } );
            }
            else
            {
                user = name;
                card = cardnum;
                return Json( new { success = true } );
            }
        }


        /// <summary>
        /// Logs a user out. This is implemented for you.
        /// </summary>
        /// <returns>Success</returns>
        [HttpPost]
        public ActionResult LogOut()
        {
            user = "";
            card = -1;
            return Json( new { success = true } );
        }

        /// <summary>
        /// Returns a JSON array representing all known books.
        /// Each book should contain the following fields:
        /// {"isbn" (string), "title" (string), "author" (string), "serial" (uint?), "name" (string)}
        /// Every object in the list should have isbn, title, and author.
        /// Books that are not in the Library's inventory (such as Dune) should have a null serial.
        /// The "name" field is the name of the Patron who currently has the book checked out (if any)
        /// Books that are not checked out should have an empty string "" for name.
        /// </summary>
        /// <returns>The JSON representation of the books</returns>
        [HttpPost]
        public ActionResult AllTitles()
        {

            var query = from T in db.Titles
                        join I in db.Inventory
                        on T.Isbn equals I.Isbn
                        into tJoinI

                        from j1 in tJoinI.DefaultIfEmpty()
                        join c in db.CheckedOut
                        on j1.Serial equals c.Serial
                        into j1JoinC

                        from j2 in j1JoinC.DefaultIfEmpty()
                        join p in db.Patrons
                        on j2.CardNum equals p.CardNum
                        into j2JoinP

                        from j in j2JoinP.DefaultIfEmpty()
                        select new
                        {
                            isbn = T.Isbn,
                            title = T.Title,
                            author = T.Author,
                            serial = j1 == null ? null : (uint?)j1.Serial,
                            name = j == null ? "" : j.Name

                        };

            return Json( query.ToArray() );

        }

        /// <summary>
        /// Returns a JSON array representing all books checked out by the logged in user 
        /// The logged in user is tracked by the global variable "card".
        /// Every object in the array should contain the following fields:
        /// {"title" (string), "author" (string), "serial" (uint) (note this is not a nullable uint) }
        /// Every object in the list should have a valid (non-null) value for each field.
        /// </summary>
        /// <returns>The JSON representation of the books</returns>
        [HttpPost]
        public ActionResult ListMyBooks()
        {
            var query = from c in db.CheckedOut
                        join i in db.Inventory
                        on c.Serial equals i.Serial
                        
                        into cJoinI

                        from j in cJoinI
                        join t in db.Titles
                        on j.Isbn equals t.Isbn
                        into jJoinT

                        from j1 in jJoinT
                        where c.CardNum == card
                        select new
                        {
                            title = j1.Title,
                            author = j1.Author,
                            serial = j.Serial,

                        };

            return Json( query );
        }


        /// <summary>
        /// Updates the database to represent that
        /// the given book is checked out by the logged in user (global variable "card").
        /// In other words, insert a row into the CheckedOut table.
        /// You can assume that the book is not currently checked out by anyone.
        /// </summary>
        /// <param name="serial">The serial number of the book to check out</param>
        /// <returns>success</returns>
        [HttpPost]
        public ActionResult CheckOutBook( int serial )
        {
            CheckedOut c = new CheckedOut();
            c.Serial = (uint)serial;
            c.CardNum = (uint)card;

            db.CheckedOut.Add(c);
            try
            {
                db.SaveChanges();
            }
            catch
            {
                //do nothing?
            }
            // You may have to cast serial to a (uint)


            return Json( new { success = true } );
        }

        /// <summary>
        /// Returns a book currently checked out by the logged in user (global variable "card").
        /// In other words, removes a row from the CheckedOut table.
        /// You can assume the book is checked out by the user.
        /// </summary>
        /// <param name="serial">The serial number of the book to return</param>
        /// <returns>Success</returns>
        [HttpPost]
        public ActionResult ReturnBook( int serial )
        {
            CheckedOut c = new CheckedOut();
            c.Serial = (uint)serial;
            c.CardNum = (uint)card;

            db.CheckedOut.Remove(c);
            try
            {
                db.SaveChanges();
            }
            catch
            {
                //do nothing?
            }
            // You may have to cast serial to a (uint)

            return Json( new { success = true } );
        }


        /*******************************************/
        /****** Do not modify below this line ******/
        /*******************************************/


        public IActionResult Index()
        {
            if ( user == "" && card == -1 )
                return View( "Login" );

            return View();
        }


        /// <summary>
        /// Return the Login page.
        /// </summary>
        /// <returns></returns>
        public IActionResult Login()
        {
            user = "";
            card = -1;

            ViewData["Message"] = "Please login.";

            return View();
        }

        /// <summary>
        /// Return the MyBooks page.
        /// </summary>
        /// <returns></returns>
        public IActionResult MyBooks()
        {
            if ( user == "" && card == -1 )
                return View( "Login" );

            return View();
        }



        public IActionResult Privacy()
        {
            return View();
        }

        [ResponseCache( Duration = 0, Location = ResponseCacheLocation.None, NoStore = true )]
        public IActionResult Error()
        {
            return View( new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier } );
        }
    }
}