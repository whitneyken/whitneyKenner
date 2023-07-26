using System;
using System.Collections.Generic;

namespace LibraryWebServer.Models;

public partial class Patrons
{
    public uint CardNum { get; set; }

    public string Name { get; set; } = null!;

    public virtual ICollection<CheckedOut> CheckedOut { get; set; } = new List<CheckedOut>();

    public virtual ICollection<Phones> Phones { get; set; } = new List<Phones>();
}
