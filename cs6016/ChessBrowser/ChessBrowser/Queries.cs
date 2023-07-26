using Microsoft.Maui.Controls;
using MySqlConnector;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ChessBrowser
{
    internal class Queries
    {

        /// <summary>
        /// This function runs when the upload button is pressed.
        /// Given a filename, parses the PGN file, and uploads
        /// each chess game to the user's database.
        /// </summary>
        /// <param name="PGNfilename">The path to the PGN file</param>
        internal static async Task InsertGameData(string PGNfilename, MainPage mainPage)
        {
            // This will build a connection string to your user's database on atr,
            // assuimg you've typed a user and password in the GUI
            string connection = mainPage.GetConnectionString();

            // TODO:
            PgnReader.PgnParser(PGNfilename);
            //       Load and parse the PGN file
            //       We recommend creating separate libraries to represent chess data and load the file

            // TODO:
            //       Use this to tell the GUI's progress bar how many total work steps there are
            //       For example, one iteration of your main upload loop could be one work step
            mainPage.SetNumWorkItems(PgnReader.allGames.Count);


            using (MySqlConnection conn = new MySqlConnection(connection))
            {
                try
                {
                    // Open a connection
                    conn.Open();
                    Console.WriteLine("Conneciton established");
                    foreach (ChessGame game in PgnReader.allGames)
                    {
                        string insertEventQueryText = "INSERT IGNORE INTO Events (Name, Site, Date) Values (@Name, @Site, @Date)";
                        string insertWhitePlayersQueryText = "INSERT INTO Players (Name, Elo) Values (@Name, @Elo) ON DUPLICATE KEY UPDATE Elo = GREATEST(Elo, @Elo)";
                        string insertBlackPlayersQueryText = "INSERT INTO Players (Name, Elo) Values (@Name, @Elo) ON DUPLICATE KEY UPDATE Elo = GREATEST(Elo, @Elo)";
                        string insertGamesQueryText = "INSERT IGNORE INTO Games (Round, Result, Moves, BlackPlayer, WhitePlayer, eID) VALUES (@Round, @Result, @Moves, @BlackPlayer, @WhitePlayer, @eID)";
                        string extractEidText = "SELECT eID from Events where Name = @Name and Site = @Site and Date = @Date";
                        string extractBlackIdText = "SELECT pID from Players where Name = @BlackPlayer";
                        string extractWhiteIdText = "SELECT PID from Players where Name = @WhitePlayer";
                        int eID, bID, wID;
                       
                        using (MySqlCommand command = new MySqlCommand(insertEventQueryText, conn))
                        {
                            command.Parameters.AddWithValue("@Name", game.EventName);
                            command.Parameters.AddWithValue("@Site", game.Site);
                            command.Parameters.AddWithValue("@Date", game.EventDate);
                            command.ExecuteNonQuery();
                        }

                        using (MySqlCommand command = new MySqlCommand(extractEidText, conn))
                        {
                            command.Parameters.AddWithValue("@Name", game.EventName);
                            command.Parameters.AddWithValue("@Site", game.Site);
                            command.Parameters.AddWithValue("@Date", game.EventDate);
                            eID = Convert.ToInt32(command.ExecuteScalar());
                        }

                        using (MySqlCommand command = new MySqlCommand(insertWhitePlayersQueryText, conn))
                        {
                            command.Parameters.AddWithValue("@Name", game.WhitePlayer);
                            command.Parameters.AddWithValue("@Elo", game.WhiteElo);
                            command.ExecuteNonQuery();
                        }

                        using (MySqlCommand command = new MySqlCommand(insertBlackPlayersQueryText, conn))
                        {
                            command.Parameters.AddWithValue("@Name", game.BlackPlayer);
                            command.Parameters.AddWithValue("@Elo", game.BlackElo);
                            command.ExecuteNonQuery();
                        }

                        using (MySqlCommand command = new MySqlCommand(extractBlackIdText, conn))
                        {
                            command.Parameters.AddWithValue("@BlackPlayer", game.BlackPlayer);
                            bID = Convert.ToInt32(command.ExecuteScalar());
                        }

                        using (MySqlCommand command = new MySqlCommand(extractWhiteIdText, conn))
                        {
                            command.Parameters.AddWithValue("@WhitePlayer", game.WhitePlayer);
                            wID = Convert.ToInt32(command.ExecuteScalar());

                        }

                        using (MySqlCommand command = new MySqlCommand(insertGamesQueryText, conn))
                        { 
                            command.Parameters.AddWithValue("@Round", game.Round);
                            command.Parameters.AddWithValue("@Result", game.Result);
                            command.Parameters.AddWithValue("@Moves", game.Moves);
                            command.Parameters.AddWithValue("@BlackPlayer", bID);
                            command.Parameters.AddWithValue("@WhitePlayer", wID);
                            command.Parameters.AddWithValue("@eID", eID);
                            command.ExecuteNonQuery();
                        }


                        await mainPage.NotifyWorkItemCompleted();

                    }

                }
                catch (Exception e)
                {
                    System.Diagnostics.Debug.WriteLine(e.Message);
                }
            }

        }


        /// <summary>
        /// Queries the database for games that match all the given filters.
        /// The filters are taken from the various controls in the GUI.
        /// </summary>
        /// <param name="white">The white player, or null if none</param>
        /// <param name="black">The black player, or null if none</param>
        /// <param name="opening">The first move, e.g. "1.e4", or null if none</param>
        /// <param name="winner">The winner as "W", "B", "D", or null if none</param>
        /// <param name="useDate">True if the filter includes a date range, False otherwise</param>
        /// <param name="start">The start of the date range</param>
        /// <param name="end">The end of the date range</param>
        /// <param name="showMoves">True if the returned data should include the PGN moves</param>
        /// <returns>A string separated by newlines containing the filtered games</returns>
        internal static string PerformQuery(string white, string black, string opening,
          string winner, bool useDate, DateTime start, DateTime end, bool showMoves,
          MainPage mainPage)
        {
            // This will build a connection string to your user's database on atr,
            // assuimg you've typed a user and password in the GUI
            string connection = mainPage.GetConnectionString();

            // Build up this string containing the results from your query
            string parsedResult = "";

            // Use this to count the number of rows returned by your query
            // (see below return statement)
            int numRows = 0;

            using (MySqlConnection conn = new MySqlConnection(connection))
            {
                try
                {
                    // Open a connection
                    conn.Open();
                    //Console.WriteLine("Inside the Perform Query function");
                    MySqlCommand cmd = conn.CreateCommand();
                    string queryText = "select E.Name, E.Site, E.Date, WP.Name, WP.Elo, BP.Name, BP.Elo, G.Result, G.Moves from Events E natural join Games G join Players WP on G.WhitePlayer = WP.pID join Players BP on G.BlackPlayer = BP.pID";

                    cmd.Parameters.AddWithValue("@WhitePlayer", white);
                    cmd.Parameters.AddWithValue("@BlackPlayer", black);
                    cmd.Parameters.AddWithValue("@opening", opening + "%");
                    cmd.Parameters.AddWithValue("@winner", winner);
                    cmd.Parameters.AddWithValue("@start", start);
                    cmd.Parameters.AddWithValue("@end", end);

                    if (white != null) {
                        queryText += " WHERE WP.Name = @WhitePlayer";
                    }

                    if (black != null)
                    {
                        queryText += " WHERE BP.Name = @BlackPlayer";
                    }

                    if (opening != null)
                    {
                        queryText += " AND G.Moves like @opening";
                    }
                    if ( winner != null)
                    {
                        queryText += " AND G.Result = @winner";
                  
                    }
                    if ( useDate )
                    {
                        queryText += " AND E.Date BETWEEN @start AND @end";
                    }

                    queryText += ";";

                    cmd.CommandText = queryText;


                    using (MySqlDataReader reader = cmd.ExecuteReader())
                    {
                        while (reader.Read())
                        {
                            parsedResult += "Event: " + reader[0] + "\n";
                            parsedResult += "Site: " + reader[1] + "\n";
                            parsedResult += "Date: " + reader[2] + "\n";
                            parsedResult += "White: " + reader[3] + " (" + reader[4] + ")\n";
                            parsedResult += "Black: " + reader[5] + " (" + reader[6] + ")\n";
                            parsedResult += "Result: " + reader[7] + "\n";
                            if (showMoves)
                            {
                                parsedResult += "Moves: " + reader[8] + "\n";
                            }
                            parsedResult += "\n";
                            numRows++;
                         }
                    }

                


                    // TODO:
                    //       Generate and execute an SQL command,
                    //       then parse the results into an appropriate string and return it.
                }
                catch (Exception e)
                {
                    System.Diagnostics.Debug.WriteLine(e.Message);
                }
            }

            return numRows + " results\n" + parsedResult;
        }

    }
}
