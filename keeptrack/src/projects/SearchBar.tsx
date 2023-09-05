import React,{useState} from 'react';
import { useEffect } from 'react';


interface Query {
    query : string;
} 

const  url = 'http://localhost:8080/api/books/fetch'

interface Book {
    title: string;
    author: string;
    pictureurl: string;
  }
// Search Bar Item, Should include picture,Title and author
const BookItem: React.FC<Book> = ({ title, author,  pictureurl }) => {
    return (
      <div className="flex items-center border p-2 bg-black text-yellow-500 shadow-md border-yellow-500 ">
        <img src={pictureurl} className="w-16 h-16 mr-2" />
        <div>
          <h2 className="text-lg font-semibold mb-1">{title}</h2>
          <p className="text-gray-400">Published: {author}</p>
        </div>
      </div>

    );
  };






// For dynamic searching using our backend API
const SearchBar: React.FC = () => {
    const [query,setQuery] = useState<Query>({query:''})
    const [searchResults, setSearchResults] = useState([]);
    const handleQueryChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setQuery({query:e.target.value})
    }
    useEffect(() => {
        // Define a function to fetch data from your API
        const fetchData = async () => {
          try {
            const response = await fetch(`/your-api-endpoint?query=${query.query}`);
            if (!response.ok) {
              throw new Error('Network response was not ok');
            }
            const data = await response.json();
            setSearchResults(data); // Update search results with data from the API
          } catch (error) {
            console.error('Error fetching data:', error);
          }
        };

        // Only fetch data when the query changes and it is not empty
        if (query.query.trim() !== '') {
          fetchData();
        } else {
          // Clear search results if the query is empty
          setSearchResults([]);
        }
      }, [query]);


return (
<div className='relative'>
  <input
    type="text"
    value={query.query}
    onChange={handleQueryChange}
    className={`w-full h-14 bg-black text-white border-2 border-yellow-500  outline-none ${query.query ? 'placeholder-transparent' : 'placeholder-yellow-500'} px-4`}
    placeholder="Search..."
  />
  {searchResults ? <div></div> : <div></div>}
</div>)






};




export default SearchBar