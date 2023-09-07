import React from "react"
import BookList from "./Booklist"
import SearchBar from "./SearchBar"
import ReccButton from "./ReccButton"
import { useState,useEffect } from "react"
// MAIN PAGE

//https://www.googleapis.com/books/v1/volumes?q=${term}&maxResults=11&key=AIzaSyCOeK3OAiQpxV7CaTPE-FAhDdI0fAFrzSA

//


let testcase  = [
    {
        "title": "The Mom Test",
        "author": "Rob Fitzpatrick",
        "isbn": "9781492180746",
        "pictureurl": "http://books.google.com/books/content?id=Z5nYDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
    },
    {
        "title": "The Official Guide to the GRE General Test, Third Edition",
        "author": "Educational Testing Service",
        "isbn": "9781259862410",
        "pictureurl": "http://books.google.com/books/content?id=K9Q_vgAACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"
    },
    {
        "title": "The Test Book",
        "author": "Mikael Krogerus",
        "isbn": "9780393247053",
        "pictureurl": "http://books.google.com/books/content?id=lPV1CQAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
    },
    {
        "title": "6 Full-Length STAAR Grade 3 Math Practice Tests",
        "author": "Michael Smith",
        "isbn": "9781646127795",
        "pictureurl": "http://books.google.com/books/content?id=yDj1DwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
    },
    {
        "title": "6 Full-Length STAAR Grade 3 Math Practice Tests",
        "author": "Michael Smith",
        "isbn": "9781646127795",
        "pictureurl": "http://books.google.com/books/content?id=yDj1DwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
    },
    {
        "title": "6 Full-Length STAAR Grade 3 Math Practice Tests",
        "author": "Michael Smith",
        "isbn": "9781646127795",
        "pictureurl": "http://books.google.com/books/content?id=yDj1DwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
    }
  ]

  interface Book {
    title: string;
    author: string;
    isbn: string;
    pictureurl: string;
  }




function ProjectsPage() {
    const [books,setBooks] = useState<Book[]>([]);
    


    return (
    <div className="bg-white flex h-4/5 mt-4 w-4/5 m-auto">
        <div className="w-2/5 border border-yellow-500 bg-black overflow-y-scroll scrollbar scrollbar-thumb-black scrollbar-track-yellow-500">
            <SearchBar/>
            <BookList bookitems={books}/>
        </div>
        <div className="w-3/5 bg-black">
            <ReccButton/>   
        </div>
        <div></div>
    </div>)




}

export default ProjectsPage