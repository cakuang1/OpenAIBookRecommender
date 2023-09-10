import React from "react";
import { useState } from "react";




interface Reccomendation {
    title: string;
    author: string;
    isbn: string;
    pictureurl: string;
    reason: string;
}

interface Reccomendationlist {
    Reclist : Reccomendation[]
}



const Reccomendations:React.FC <Reccomendationlist>= ({Reclist}) => {
    return (
        <div>
            {Reclist.map((item,index) => (
                <div className="text-yellow-500">
                <div className="flex items-center border p-2 bg-black  shadow-md border-yellow-500 hover:bg-red-500 transition duration-300 ease-in-out">
                    <img src={item.pictureurl} className="w-16 h-24 mr-2" />
                        <div>
                          <h2 className="text-lg font-semibold mb-1">{item.title}</h2>
                          <p className="text-gray-400">Published: {item.author}</p>
                        </div>
                </div>
                <div className="text-center">
                    <h1>Why</h1>
                    <p>{item.reason}</p>
                </div>
                </div>
            ))}
      </div>
    )
}


const Recommendedsection:React.FC = () => {
    const [data, setData] <Reccomendationlist>= useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const fetchData = async () => {
      try {
        setIsLoading(true);
  
        // Make an API call using fetch or axios here
        const response = await fetch('your-api-endpoint');
        const result = await response.json();
  
        setData(result);
      } catch (error) {
        console.error('Error fetching data:', error);
      } finally {
        setIsLoading(false);
      }
    };

    return (
    <div className="flex">
    <div className="text-yellow-500 text-center mx-2 w-1/3">
        <h1 className="font-bold text-4xl">How To</h1>
        <p className="mt-2">Add your favorite books using the searchbar and click the button below to allow AI to generate reccomendations</p>
        <div>
        <button onClick={fetchData} disabled={isLoading}>
            {isLoading ? 'Loading...' : 'Fetch Data'}
        </button>
        </div>
    </div>
    <div className="bg-white w-2/3">
        <Reccomendations />
    </div>
    </div>)


}

export default Recommendedsection