package com.example.netflixreplicated.data.repositories

import com.example.netflixreplicated.data.models.Film
import com.example.netflixreplicated.data.models.FilmCategory

class FilmRepository: FilmRepositoryImpl{

    override suspend fun getRecentlyAddedList(): List<Film> {
        val dummyList = mutableListOf<Film>()
        dummyList.add(Film("Movie 1","https://www.themoviedb.org/t/p/original/vZloFAK7NmvMGKE7VkF5UHaz0I.jpg",false , FilmCategory.ACTION))
        dummyList.add(Film("Movie 1","https://www.themoviedb.org/t/p/original/nUCRLTlCAlCvuZtdoO0P0u0YFay.jpg",true , FilmCategory.ACTION))
        dummyList.add(Film("Movie 1","https://www.themoviedb.org/t/p/original/8CYSvYTw9tbFynivdcBcoqRWPGM.jpg",false , FilmCategory.ACTION))
        dummyList.add(Film("Movie 1","https://www.themoviedb.org/t/p/original/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg",false , FilmCategory.ACTION))
        dummyList.add(Film("Movie 1","https://www.themoviedb.org/t/p/original/1E5baAaEse26fej7uHcjOgEE2t2.jpg",true , FilmCategory.ACTION))
        dummyList.add(Film("Movie 1","https://www.themoviedb.org/t/p/original/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg",false , FilmCategory.ACTION))
        dummyList.add(Film("Movie 1","https://www.themoviedb.org/t/p/original/tIcbs6fN8WHSo6iFETz23l2N8tl.jpg",true , FilmCategory.ACTION))
        dummyList.add(Film("Movie 1","https://www.themoviedb.org/t/p/original/cvsXj3I9Q2iyyIo95AecSd1tad7.jpg",true , FilmCategory.ACTION))
        dummyList.add(Film("The Best Man","https://www.themoviedb.org/t/p/original/c9f6mFZqkyz4AD0sxGmynE1pe0v.jpg",false , FilmCategory.ACTION))
        dummyList.add(Film("Guardians of the Galaxy","https://www.themoviedb.org/t/p/original/r2J02Z2OpNTctfOSN1Ydgii51I3.jpg",false , FilmCategory.ACTION))
        dummyList.add(Film("Forrest Gump","https://www.themoviedb.org/t/p/original/arw2vcBveWOVZr6pxd9XTd1TdQa.jpg",true , FilmCategory.ACTION))
        dummyList.add(Film("The Dark Knight","https://www.themoviedb.org/t/p/original/qJ2tW6WMUDux911r6m7haRef0WH.jpg",false , FilmCategory.ACTION))
        dummyList.add(Film("Transformers Rise of the Beasts","https://www.themoviedb.org/t/p/original/gPbM0MK8CP8A174rmUwGsADNYKD.jpg",false , FilmCategory.ACTION))
        return dummyList
    }

    override suspend fun getTopTenList(): List<Film> {
        val dummyList = mutableListOf<Film>()
        dummyList.add(Film("Movie 1","https://www.themoviedb.org/t/p/original/vZloFAK7NmvMGKE7VkF5UHaz0I.jpg",false , FilmCategory.ACTION))
        dummyList.add(Film("Movie 1","https://www.themoviedb.org/t/p/original/nUCRLTlCAlCvuZtdoO0P0u0YFay.jpg",true , FilmCategory.ACTION))
        dummyList.add(Film("Movie 1","https://www.themoviedb.org/t/p/original/8CYSvYTw9tbFynivdcBcoqRWPGM.jpg",false , FilmCategory.ACTION))
        dummyList.add(Film("Movie 1","https://www.themoviedb.org/t/p/original/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg",false , FilmCategory.ACTION))
        dummyList.add(Film("Movie 1","https://www.themoviedb.org/t/p/original/1E5baAaEse26fej7uHcjOgEE2t2.jpg",true , FilmCategory.ACTION))
        dummyList.add(Film("Movie 1","https://www.themoviedb.org/t/p/original/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg",false , FilmCategory.ACTION))
        dummyList.add(Film("Movie 1","https://www.themoviedb.org/t/p/original/tIcbs6fN8WHSo6iFETz23l2N8tl.jpg",true , FilmCategory.ACTION))
        dummyList.add(Film("Movie 1","https://www.themoviedb.org/t/p/original/cvsXj3I9Q2iyyIo95AecSd1tad7.jpg",true , FilmCategory.ACTION))
        dummyList.add(Film("The Best Man","https://www.themoviedb.org/t/p/original/c9f6mFZqkyz4AD0sxGmynE1pe0v.jpg",false , FilmCategory.ACTION))
        dummyList.add(Film("Guardians of the Galaxy","https://www.themoviedb.org/t/p/original/r2J02Z2OpNTctfOSN1Ydgii51I3.jpg",false , FilmCategory.ACTION))
        return dummyList
    }

}