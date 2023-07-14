using System;
using System.Collections.Generic;

namespace LibraryWebServer.Models;

public partial class Inventory
{
    public uint Serial { get; set; }

    public string Isbn { get; set; } = null!;

    public virtual CheckedOut? CheckedOut { get; set; }

    public virtual Titles IsbnNavigation { get; set; } = null!;
}
