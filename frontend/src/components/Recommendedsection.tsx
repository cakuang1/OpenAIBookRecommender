import React from "react";
import { useState,CSSProperties } from "react";
import ClipLoader from 'react-spinners/ClipLoader';


interface Reccomendation {
    title: string;
    author: string;
    isbn: string;
    pictureUrl: string;
    reason: string;
}

interface Reccomendationlist {
    reclist : Reccomendation[]
}





const Reccomendations:React.FC <Reccomendationlist>= ({reclist}) => {
  console.log(reclist)
    return (
        <div>
            {reclist.map((item,index) => (
                <div className="text-yellow-500 bg-black border border-yellow-500 p-2 text-center">
                    <img src={item.pictureUrl} className="w-16 h-24 mx-auto" />
                        <div>
                          <h2 className="text-lg font-semibold mb-1">{item.title}</h2>
                          <p className="text-gray-400">Author: {item.author}</p>
                        </div>
                <div className="text-center">
                    <h1 className="font-bold">Reason</h1>
                    <p>{item.reason}</p>
                </div>
                </div>
            ))}
      </div>
    )
}

const Recommendedsection:React.FC = () => {
  const [data, setData] = useState<Reccomendation[]>([
]);
    const [isLoading, setIsLoading] = useState(false);
    let [color, setColor] = useState("#ffffff");
    const fetchData = async () => {
      try {
        setIsLoading(true);
        // Make an API call using fetch or axios here
        const response = await fetch('http://localhost:8085/sessions/getmovierecs',{credentials: 'include'});
        const result = await response.json();
        console.log(result)
        setData(result);
      } catch (error) {
        console.error('Error fetching data:', error);
      } finally {
        setIsLoading(false);
      }
    };
    return (
    <div className="flex h-full">
    <div className="text-yellow-500 text-center mx-2 w-1/3 my-32">
        <h1 className="font-bold text-4xl">How To</h1>
        <p className="mt-2">Add your favorite books using the searchbar and click the button below to allow AI to generate reccomendations</p>
        <div>
        <button
        onClick={fetchData}
        disabled={isLoading}
        className="h-32 w-full bg-yellow-500 hover:bg-yellow-600 text-white font-semibold py-2 px-4 rounded focus:outline-none focus:ring focus:ring-yellow-400"
      >
        Recommend
      </button>
        </div>
    </div>
    
    <div className="w-2/3 border border-yellow-500 bg-black  justify-center overflow-y-scroll scrollbar scrollbar-thumb-black scrollbar-track-yellow-500">
    {isLoading?     <div className="flex justify-center">  <ClipLoader
    className=""
    color="#F59E0B"
    size={125}
    aria-label="Loading Spinner"
    data-testid="loader"
  /></div> :
  <Reccomendations reclist={data}/>}



</div>
    </div>)


}

export default Recommendedsection