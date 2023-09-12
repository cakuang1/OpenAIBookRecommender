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
    reclist : Reccomendation[]
}





const Reccomendations:React.FC <Reccomendationlist>= ({reclist}) => {
    return (
        <div>
            {reclist.map((item,index) => (
                <div className="text-yellow-500 bg-black border border-yellow-500 p-2 text-center">
                    <img src={item.pictureurl} className="w-16 h-24 mx-auto" />
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
  {
      "reason":"noreaseon",
      "title": "The Test Book",
      "author": "Mikael Krogerus",
      "isbn": "9780393247053",
      "pictureurl": "http://books.google.com/books/content?id=lPV1CQAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
  },
  {
    "reason":"noreaseon",
      "title": "The Official Guide to the GRE General Test, Third Edition",
      "author": "Educational Testing Service",
      "isbn": "9781259862410",
      "pictureurl": "http://books.google.com/books/content?id=K9Q_vgAACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"
  }
]);
    const [isLoading, setIsLoading] = useState(false);
    const fetchData = async () => {
      try {
        setIsLoading(true);
        // Make an API call using fetch or axios here
        const response = await fetch('http://localhost:8085/sessions/getmovierecs',{credentials: 'include'});
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
        <button
        onClick={fetchData}
        disabled={isLoading}
        className="bg-yellow-500 hover:bg-yellow-600 text-white font-semibold py-2 px-4 rounded focus:outline-none focus:ring focus:ring-yellow-400"
      >
        Recommend
      </button>

        </div>
    </div>
    <div className="bg-white w-2/3">
        <Reccomendations reclist={data}/>
    </div>
    </div>)


}

export default Recommendedsection