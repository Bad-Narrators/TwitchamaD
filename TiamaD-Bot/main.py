from twitchio.ext import commands
from dotenv import load_dotenv
from os import environ
import requests


class Bot(commands.Bot):

    def __init__(self, token):
        # Initialise our Bot with our access token, prefix and a list of channels to join on boot...
        # prefix can be a callable, which returns a list of strings or a string...
        # initial_channels can also be a callable which returns a list of strings...
        super().__init__(token=token, prefix='?', initial_channels=['luciusheit'])

    async def event_ready(self):
        # Notify us when everything is ready!
        # We are logged in and ready to chat and use commands...
        print(f'Logged in as | {self.nick}')
        print(f'User id is | {self.user_id}')

    async def event_message(self, message):
        # Messages with echo set to True are messages sent by the bot...
        # For now we just want to ignore them...
        if message.echo:
            return

        # Print the contents of our message to console...
        print(f'Message: {message.content} from {message.author.name}')

        # Since we have commands and are overriding the default `event_message`
        # We must let the bot know we want to handle and invoke our commands...
        await self.handle_commands(message)

    @commands.command()
    async def hello(self, ctx: commands.Context):
        # Here we have a command hello, we can invoke our command with our prefix and command name
        # e.g ?hello
        # We can also give our commands aliases (different names) to invoke with.

        # Send a hello back!
        # Sending a reply back to the channel is easy... Below is an example.
        await ctx.send(f'Hello {ctx.author.name}!')

    # twitch command to create a poll
    @commands.command()
    async def createPoll(self, ctx: commands.Context, *, question):
        url = "http:localhost:8080/api/poll/getByQuestion?question="+question
        response = requests.get(url)
        if response.status_code == 200:
            await ctx.send(f'Poll already exists!')
        else:
            url = "http:localhost:8080/api/poll/create?question="+question
            response = requests.get(url)
            if response.status_code == 200:
                await ctx.send(f'Poll created!')
            else:
                await ctx.send(f'Poll not created!')
        await ctx.send(f'Poll: {question}')

    # twitch command to add a vote to a poll
    @commands.command()
    async def vote(self, ctx: commands.Context, *, vote):
        url = "http:localhost:8080/api/poll/vote?question="+vote
        response = requests.get(url)
        if response.status_code == 200:
            await ctx.send(f'Vote added!')
        else:
            await ctx.send(f'Vote not added!')
        await ctx.send(f'Vote: {vote}')

load_dotenv()
token = environ['ACCESS_TOKEN']
bot = Bot(token)
bot.run()
# bot.run() is blocking and will stop execution of any below code here until stopped or closed.
