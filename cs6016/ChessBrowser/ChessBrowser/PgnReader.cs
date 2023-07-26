using System;
using Microsoft.Maui.Controls;
using static System.Net.Mime.MediaTypeNames;

namespace ChessBrowser
{
	public static class PgnReader
	{
        public static List<ChessGame> allGames;

        // provides methods to return (for example) a List<ChessGame> given a path to a PGN file
        public static void PgnParser (string path)
        {
            allGames = new List<ChessGame>();

            ChessGame currentGame = null;

            string[] gameRecord = File.ReadAllLines(path);
            Console.WriteLine("++++Running the parser++++");

            foreach (string i in gameRecord)
            {
                if (i.StartsWith("[Event "))
                {
                    currentGame = new ChessGame();
                    string nameString = betweenQuotes(i);
                    currentGame.EventName = nameString;
                    allGames.Add(currentGame);
                }
                if (i.StartsWith("[Site "))
                {
                    string siteString = betweenQuotes(i);
                    currentGame.Site = siteString;
                }
                if (i.StartsWith("[Date "))
                {
                    string dateString = betweenQuotes(i);
                    currentGame.Date = dateString;
                }
                if (i.StartsWith("[Round "))
                {
                    string roundString = betweenQuotes(i);
                    currentGame.Round = roundString;
                }
                if (i.StartsWith("[White "))
                {
                    string whiteString = betweenQuotes(i);
                    currentGame.WhitePlayer = whiteString;
                }
                if (i.StartsWith("[Black "))
                {
                    string blackString = betweenQuotes(i);
                    currentGame.BlackPlayer = blackString;
                }
                if (i.StartsWith("[Result "))
                {
                    string resultString = betweenQuotes(i);
                    if (resultString == "1-0")
                    {
                        currentGame.Result = "W";
                    }
                    else if (resultString == "0-1")
                    {
                        currentGame.Result = "B";
                    }
                    else if (resultString == "1/2-1/2")
                    {
                        currentGame.Result = "D";
                    }
                    else
                    {
                        currentGame.Result = "Error"; 
                    }
                }
                if (i.StartsWith("[WhiteElo "))
                {
                    string whiteEloString = betweenQuotes(i);
                    currentGame.WhiteElo = whiteEloString;
                }
                if (i.StartsWith("[BlackElo "))
                {
                    string blackEloString = betweenQuotes(i);
                    currentGame.BlackElo = blackEloString;
                }
                if (i.StartsWith("[ECO "))
                {
                    string ecoString = betweenQuotes(i);
                    currentGame.ECO = ecoString;
                }
                if (i.StartsWith("[EventDate "))
                {
                    string eventDateString = betweenQuotes(i);
                    currentGame.EventDate = eventDateString;
                }
                if (!i.StartsWith("["))
                {
                    currentGame.Moves += i;
                }
            }

        }

        public static string betweenQuotes (string text)
        {
            int firstColon = text.IndexOf("\"");
            int secondColon = text.LastIndexOf("\"");
            string textBetween = text.Substring(firstColon + 1, secondColon - firstColon - 1);
            return textBetween;
        }



    }
}

