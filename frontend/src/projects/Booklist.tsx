import React from "react";




// BOOK LIST Used for both the User Book List and Reccomendation Book List



interface Book {
    title: string;
    author: string;
    isbn: string;
    pictureurl: string;
  }

  const BookItem: React.FC<Book> = ({ title ,author, isbn, pictureurl }) => {
    return (
      <div className="flex items-center border p-2 bg-black text-yellow-500 shadow-md border-yellow-500 ">
        <img src={pictureurl} className="w-16 h-24 mr-2" />
        <div>
          <h2 className="text-lg font-semibold mb-1">{title}</h2>
          <p className="text-gray-400">ISBN: {isbn}</p>
          <p className="text-gray-400">Published: {author}</p>
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
            title={book.title}
            pictureurl={book.pictureurl}
            author={book.author
            }
          />
        ))}
      </div>
    );
  };


export default BookList