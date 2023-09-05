import BookList from "./Booklist"
import SearchBar from "./SearchBar"
// MAIN PAGE



//https://www.googleapis.com/books/v1/volumes?q=${term}&maxResults=11

//AIzaSyCOeK3OAiQpxV7CaTPE-FAhDdI0fAFrzSA





const testbooklist = [
    {},
    {},
    {}



]



function ProjectsPage() {
    return (
    <div className="bg-white flex h-4/5 mt-4 w-4/5 m-auto">
        <div className="w-2/5">
            <SearchBar/>
            <BookList />
        </div>
        <div className="w-1/5 bg-black"></div>
        <div></div>



    </div>)




}

export default ProjectsPage