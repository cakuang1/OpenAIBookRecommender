import React from "react";




// BOOK LIST Used for both the User Book List and Reccomendation Book List






interface Book {
    isbn: string;
    name: string;
    picture: string;
    publish: string;
  }
  
  const BookItem: React.FC<Book> = ({ isbn, name, picture, publish }) => {
    return (
      <div className="flex items-center border rounded-md p-4 shadow-md mb-4">
        <img src={picture} alt={name} className="w-24 h-36 mr-4" />
        <div>
          <h2 className="text-xl font-semibold mb-2">{name}</h2>
          <p className="text-gray-600">ISBN: {isbn}</p>
          <p className="text-gray-600">Published: {publish}</p>
        </div>
      </div>
    );
  };
  
  interface ListofBooks {
    bookitems: Book[];
  }
  
  const BookList: React.FC<ListofBooks> = ({ bookitems }) => {
    return (
      <div>
        {bookitems.map((book, index) => (
          <BookItem
            key={index} 
            isbn={book.isbn}
            name={book.name}
            picture={book.picture}
            publish={book.publish}
          />
        ))}
      </div>
    );
  };


export default BookList