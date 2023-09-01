import React from "react";
import SearchBar from "./SearchBar";




interface Book {
    isbn: string;
    name: string;
    picture: string;
    publish: string;
  }


const Movieitem : React.FC<Book> = ({isbn,name,picture,publish}) => {
    return (
        <div className="flex">
            

        </div>




    )



}
interface ListofMovies {
    movieitems : Book[];
}





const Movielist : React.FC<ListofMovies> = ({movieitems}) => {
    return (
    <div>
        
        
    </div>)


}


export default Movielist