import React,{useState} from 'react';


interface Query {
    query : string;
} 






const SearchBar: React.FC = () => {
    const [query,setQuery] = useState<Query>({query:''});
    const handleQueryChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setQuery({query:e.target.value})
    }






    

return (<div className=''>
    <input type="text" value={query.query} onChange={handleQueryChange} className='w-full'/>  



        </div>)






};




export default SearchBar