using System;
using System.Collections.Generic;

namespace LibraryWebServer.Models;

public partial class Titles
{
    public string Isbn { get; set; } = null!;

    public string Title { get; set; } = null!;

    public string Author { get; set; } = null!;

    public virtual ICollection<Inventory> Inventory { get; set; } = new List<Inventory>();
}
