from flask import Flask,request, jsonify
import json
import openai
import os
from dotenv import load_dotenv
# Load environment variables from .env file
load_dotenv()
OPENAIAPIKEY = os.getenv('API_KEY')

openai.api_key = OPENAIAPIKEY


# Entry 
app = Flask(__name__)

@app.route('/api/books', methods=['GET'])
def get_books():
    book_ids = request.args.getlist('id')
    books_read = ['The Lightning Thief', 'Fahrenheit 451', 'Harry Potter']
    prompt = "Given I have read the books " + str(books_read) + ",give me other book reccomendations and why in json"
    completion = openai.ChatCompletion.create(model="gpt-3.5-turbo", messages=[{"role": "user", "content": prompt}])
    data = json.loads(completion.choices[0].message.content)
    return jsonify(data)

if __name__ == '__main__':
    app.run(host='localhost', port=9874)
