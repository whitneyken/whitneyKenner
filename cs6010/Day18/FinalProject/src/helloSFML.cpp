#include <SFML/Graphics.hpp>

int main()
{
    // create the window
    sf::RenderWindow window(sf::VideoMode(800, 600), "My window");

    // run the program as long as the window is open
    while (window.isOpen())
    {
        // check all the window's events that were triggered since the last iteration of the loop
        sf::Event event;
        while (window.pollEvent(event))
        {
            // "close requested" event: we close the window
            if (event.type == sf::Event::Closed)
                window.close();
        }

        // clear the window with black color
        window.clear(sf::Color::Black);

	sf::CircleShape shape(100.f);
    sf::RectangleShape shape2(sf::Vector2f(100.f, 90.f));
    shape2.setPosition(270.f, 50.f);

// set the shape color to green
shape.setFillColor(sf::Color(150, 150, 100));
        
shape.setOutlineThickness(-20.f);
shape.setOutlineColor(sf::Color(250, 150, 100));

 window.draw(shape);
window.draw(shape2);
	// end the current frame
        window.display();
    }
    

    return 0;
}
