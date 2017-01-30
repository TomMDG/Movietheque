# Movietheque
A Java program to archive and display personal collections of movies, using an IMDb API to find all the related datas

<strong>List of the different classes:</strong>

Display: main class that starts the application
BackgroundPanel: home panel of the software
<ul>
<li>AddMovieButton: button to add a movie to the collection</li>
<li>MoviePosterButton: button to go to the DetailedMoviePanel</li>
<li>ListMoviePosterButton: list of MoviePosterButtons</li>
<li>ComparatorTitleMPB: comparator to sort a ListMoviePosterButton</li>
</ul>

DetailedMoviePanel: panel displaying all the datas extracted from an IMDb API related to the movie
<ul>
<li>BackButton: button to go to the BackgroundPanel</li>
<li>DeleteButton: button to delete the movie from the collection</li>
<li>PlayButton: button to play a movie using the default video player</li>
<li>LikeButton: button to like/unlike the movie</li>
<li>WatchedButton: button to precise if the user already saw the movie</li>
<li>MoviePosterPanel: panel that displays the movie's poster</li>
<li>IMDbRatingPanel: panel displaying the IMDb's rating</li>
<li>MovieTitlePanel: panel displaying the movie's title</li>
<li>MovieInfosPanel: panel displaying others datas (directors, actors, plot, genre...)</li>
</ul>

Movie: class corresponding to movies
<ul>
<li>ListMovie: list of Movies</li>
<li>ComparatorTitle: comparator to sort a ListMovie</li>
<li>ImageResizer: class to resize posters extracted from IMDb API</li>
<li>MoviePosterNotFoundImage: create a default poster when the poster is not found on the IMDb API</li>

WindowClosingListener: listener to shut down the software when the user closes the window
NameException: exception thrown when the movie is not found on IMDb API
SourceFilesMissingException: IOException thrown when some source files are missing



